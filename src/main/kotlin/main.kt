import dao.repository.impl.TransactionRepositoryImpl
import java.time.Month
import java.util.*

fun main(args: Array<String>) {

    val transactionRepository = TransactionRepositoryImpl()

    transactionRepository.getTransactions()

    transactionRepository.getTransactionsByMonth(Month.MARCH)
}