package bo

import dao.entity.enum.Status
import dao.repository.filters.FilterTransactionsDTO
import dao.repository.impl.TransactionRepositoryImpl
import java.time.Month

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

            val pendingTransactions = transactionRepository.findAll(filters = FilterTransactionsDTO
                .Builder()
                .month(month = month)
                .status(status = Status.PENDING)
                .build()
            )


            val rejectedTransactions = transactionRepository.findAll(filters = FilterTransactionsDTO
                .Builder()
                .month(month = month)
                .status(Status.REJECTED)
                .build()
            )

        }

    }

    /**
     *  Create transaction report by month
     */

}