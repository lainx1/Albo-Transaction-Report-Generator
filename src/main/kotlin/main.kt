import dao.repository.impl.TransactionRepositoryImpl
import java.time.Month

fun main(args: Array<String>) {

    val transactionRepository = TransactionRepositoryImpl()

    transactionRepository.findAll()

    transactionRepository.findTransactionsByMonth(Month.MARCH)
}