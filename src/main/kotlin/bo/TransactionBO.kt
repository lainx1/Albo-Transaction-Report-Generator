package bo

import dao.entity.enum.Category
import dao.entity.enum.Operation
import dao.entity.enum.Status
import dao.repository.filters.FilterTransactionsDTO
import dao.repository.impl.TransactionRepositoryImpl
import dto.CategoryReportDTO
import dto.MonthlyTransactionReportDTO
import utils.NumberUtils
import java.math.BigDecimal
import java.time.Month
import java.time.format.TextStyle
import java.util.*

/**
 * This class contains the busyness object for Transactions.
 * @author Ivan Martinez Jimenez.
 */
class TransactionBO {

    /**
     * Transaction repository.
     */
    private val transactionRepository = TransactionRepositoryImpl()

    /**
     * Print a report for transactions for all months.
     *
     */
    fun generateReport(){
        for(month in Month.values()){
            println(generateReportByMonth(month = month).toString())
        }
    }

    /**
     *  Create transaction report by month
     */
    private fun generateReportByMonth(month: Month) : MonthlyTransactionReportDTO{

        val pendingTransactions = transactionRepository.findAll(filters = FilterTransactionsDTO
            .Builder()
            .month(month = month)
            .status(status = Status.PENDING)
            .build()
        )


        val rejectedTransactions = transactionRepository.findAll(filters = FilterTransactionsDTO
            .Builder()
            .month(month = month)
            .status(status = Status.REJECTED)
            .build()
        )

        var incomeAmount = BigDecimal.ZERO
        transactionRepository.findAll(filters = FilterTransactionsDTO
            .Builder()
            .month(month = month)
            .operation(operation = Operation.IN)
            .status(status = Status.DONE)
            .build()
        ).stream().map { it.amount }.forEach { incomeAmount = incomeAmount.add(it) }

        var outcomeAmount = BigDecimal.ZERO
        transactionRepository.findAll(filters = FilterTransactionsDTO
            .Builder()
            .month(month = month)
            .operation(operation = Operation.OUT)
            .status(status = Status.DONE)
            .build()
        ).stream().map { it.amount }.forEach { outcomeAmount = outcomeAmount.add(it) }

        val categoryReports = mutableListOf<CategoryReportDTO>()

        for (category in Category.values()){

            var categoryTotalAmount = BigDecimal.ZERO
            var categoryOutAmount = BigDecimal.ZERO

            transactionRepository.findAll(FilterTransactionsDTO
                .Builder()
                .category(category = category)
                .month(month)
                .build()
            ).stream().map { it.amount }.forEach { categoryTotalAmount = categoryTotalAmount.add(it) }

            transactionRepository.findAll(FilterTransactionsDTO
                .Builder()
                .category(category = category)
                .operation(Operation.OUT)
                .month(month)
                .build()
            ).stream().map { it.amount }.forEach { categoryOutAmount = categoryOutAmount.add(it) }

            var percentage = (categoryTotalAmount.multiply(categoryOutAmount)).divide(BigDecimal("100.0"))

            if (categoryTotalAmount > BigDecimal.ZERO)

            categoryReports.add(CategoryReportDTO
                .Builder()
                .category(category)
                .amount(percentage)
                .build()
            )
        }

        with(categoryReports){
            this.sortBy { it.amount }
            this.reverse()
        }


        return MonthlyTransactionReportDTO
            .Builder()
            .month(month = month.getDisplayName(TextStyle.FULL, Locale("es", "MX")))
            .pendingTransactions(pendingTransactions = pendingTransactions.size)
            .rejectedTransactions(rejectedTransactions = rejectedTransactions.size)
            .incomeAmount(incomeAmount = NumberUtils.convertToDecimals(number = incomeAmount))
            .outcomeAmount(outcomeAmount = NumberUtils.convertToDecimals(number = outcomeAmount))
            .categoryReports(categoryReports = categoryReports)
            .build()

    }

}