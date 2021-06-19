package dao.repository.impl

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dao.entity.Transaction
import dao.entity.enum.adapters.CategoryAdapter
import dao.entity.enum.adapters.DateAdapter
import dao.entity.enum.adapters.OperationAdapter
import dao.entity.enum.adapters.StatusAdapter
import dao.repository.TransactionRepository
import java.io.File
import java.io.InputStream
import java.lang.reflect.ParameterizedType
import java.util.*

/**
 * This class contains the implementation for repository.
 * @author Ivan Martinez Jimenez.
 */
class TransactionRepositoryImpl  : TransactionRepository{

    override fun getTransactions(): List<Transaction> {

        val inputStream : InputStream = File(".\\src\\main\\kotlin\\dao\\source\\Transactions.json").inputStream()

        val transactionsJson = inputStream.bufferedReader().use { it.readText() }

        val moshi =  Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(CategoryAdapter())
            .add(OperationAdapter())
            .add(StatusAdapter())
            .add(DateAdapter())
            .build()

        val listOfTransaction : ParameterizedType = Types.newParameterizedType(List::class.java, Transaction::class.java)
        val jsonAdapter = moshi.adapter<List<Transaction>>(listOfTransaction)


        return Optional.ofNullable(jsonAdapter.fromJson(transactionsJson)).orElse(mutableListOf())
    }

}