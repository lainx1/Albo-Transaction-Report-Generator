package dao.repository

import dao.entity.Transaction
import java.time.Month

/**
 * This class contains the Transaction repository.
 * @author Ivan Martinez Jimenez.
 */
interface TransactionRepository {

    /**
     * Retrieve all transactions.
     *
     * @return all transactions.
     */
    fun getTransactions(): List<Transaction>


    /**
     * Retrieve all transactions by specified month.
     * @return transactions by month.
     */
    fun getTransactionsByMonth(month: Month): List<Transaction>

}