package bo

import dao.entity.enum.Operation
import dao.entity.enum.Status
import dao.repository.filters.FilterTransactionsDTO
import dao.repository.impl.TransactionRepositoryImpl
import dto.MonthlyTransactionReportDTO
import utils.NumberUtils
import java.math.BigDecimal
import java.time.Month
import java.util.stream.Collectors

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

        return MonthlyTransactionReportDTO
            .Builder()
            .pendingTransactions(pendingTransactions = pendingTransactions.size)
            .rejectedTransactions(rejectedTransactions = rejectedTransactions.size)
            .incomeAmount(incomeAmount = NumberUtils.convertToDecimals(number = incomeAmount))
            .outcomeAmount(outcomeAmount = NumberUtils.convertToDecimals(number = outcomeAmount))
            .build()

    }

}