package dao.repository

import dao.entity.Transaction

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
    fun getTransactions() : List<Transaction>

}