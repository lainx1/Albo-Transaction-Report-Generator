package dao.repository

import dao.entity.Transaction
import dao.repository.filters.FilterTransactionsDTO

/**
 * This class contains the Transaction repository.
 * @author Ivan Martinez Jimenez.
 */
interface TransactionRepository {

    /**
     * Retrieve all transactions.
     *
     * @return transactions given filters.
     */
    fun findAll(): List<Transaction>

    /**
     * Retrieve transactions given param.
     * @param filterTransactionsDTO filter for transactions.
     *
     * @return transactions given filters.
     */
    fun findAll(filterTransactionsDTO: FilterTransactionsDTO): List<Transaction>

}