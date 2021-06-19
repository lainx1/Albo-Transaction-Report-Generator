package dao.repository.impl

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dao.entity.Transaction
import dao.entity.adapters.CategoryAdapter
import dao.entity.adapters.DateAdapter
import dao.entity.adapters.OperationAdapter
import dao.entity.adapters.StatusAdapter
import dao.repository.TransactionRepository
import dao.repository.filters.FilterTransactionsDTO
import utils.DateUtils
import java.io.File
import java.io.InputStream
import java.lang.reflect.ParameterizedType
import java.util.*
import java.util.stream.Collectors

/**
 * This class contains the implementation for repository.
 * @author Ivan Martinez Jimenez.
 */
class TransactionRepositoryImpl : TransactionRepository {

    override fun findAll(): List<Transaction> {

        val inputStream: InputStream = File(".\\src\\main\\kotlin\\dao\\source\\Transactions.json").inputStream()

        val transactionsJson = inputStream.bufferedReader().use { it.readText() }

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(CategoryAdapter())
            .add(OperationAdapter())
            .add(StatusAdapter())
            .add(DateAdapter())
            .build()

        val listOfTransaction: ParameterizedType = Types.newParameterizedType(List::class.java, Transaction::class.java)
        val jsonAdapter = moshi.adapter<List<Transaction>>(listOfTransaction)


        return Optional.ofNullable(jsonAdapter.fromJson(transactionsJson)).orElse(mutableListOf())
    }

    override fun findAll(filters: FilterTransactionsDTO): List<Transaction> {

        var transactions = findAll()

        val filterByStatus = filters.status
        val filterByOperation = filters.operation
        val filterByMonth = filters.month
        val filterByCategory = filters.category


        if (filterByStatus != null)
            transactions = transactions.stream().filter { it.status == filterByStatus }.collect(Collectors.toList())

        if (filterByOperation != null)
            transactions = transactions.stream().filter { it.operation == filterByOperation}.collect(Collectors.toList())

        if(filterByMonth != null)
            transactions = transactions.stream().filter { DateUtils.getMonth(it.creation_date) == filterByMonth }.collect(Collectors.toList())

        if(filterByCategory != null)
            transactions = transactions.stream().filter { it.category == filterByCategory }.collect(Collectors.toList())


        return transactions
    }

}