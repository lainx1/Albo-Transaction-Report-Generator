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
import java.math.RoundingMode
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
    fun generateReport() {
        for (month in Month.values()) {
            println(generateReportByMonth(month = month).toString())
        }
    }

    /**
     *  Create transaction report by month
     */
    private fun generateReportByMonth(month: Month): MonthlyTransactionReportDTO {

        /**
         * Pending transactions.
         */
        val pendingTransactions = transactionRepository.findAll(
            filters = FilterTransactionsDTO
                .Builder()
                .month(month = month)
                .status(status = Status.PENDING)
                .build()
        )


        /**
         * Rejected transactions.
         */
        val rejectedTransactions = transactionRepository.findAll(
            filters = FilterTransactionsDTO
                .Builder()
                .month(month = month)
                .status(status = Status.REJECTED)
                .build()
        )

        /**
         * Income amount
         */
        var incomeAmount = BigDecimal.ZERO
        transactionRepository.findAll(
            filters = FilterTransactionsDTO
                .Builder()
                .month(month = month)
                .operation(operation = Operation.IN)
                .status(status = Status.DONE)
                .build()
        ).stream().map { it.amount }.forEach { incomeAmount = incomeAmount.add(it) }

        /**
         * Outcome amount
         */
        var outcomeAmount = BigDecimal.ZERO
        transactionRepository.findAll(
            filters = FilterTransactionsDTO
                .Builder()
                .month(month = month)
                .operation(operation = Operation.OUT)
                .status(status = Status.DONE)
                .build()
        ).stream().map { it.amount }.forEach { outcomeAmount = outcomeAmount.add(it) }

        /**
         * Category report.
         */
        val categoryReports = mutableListOf<CategoryReportDTO>()

        for (category in Category.values()) {

            var categoryOutAmount = BigDecimal.ZERO

            val transactions = transactionRepository.findAll(
                FilterTransactionsDTO
                    .Builder()
                    .category(category = category)
                    .operation(Operation.OUT)
                    .status(Status.DONE)
                    .month(month)
                    .build()
            )

            transactions.stream().map { it.amount }.forEach { categoryOutAmount = categoryOutAmount.add(it) }

            val percentage =
                (categoryOutAmount.multiply(BigDecimal("100.0"))).divide(outcomeAmount, RoundingMode.CEILING)

            if (transactions.isNotEmpty())

                categoryReports.add(
                    CategoryReportDTO
                        .Builder()
                        .category(category)
                        .amount(percentage)
                        .build()
                )
        }

        /**
         * Sort category bigger to low
         */
        with(categoryReports) {
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