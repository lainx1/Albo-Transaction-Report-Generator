import dao.repository.impl.TransactionRepositoryImpl

fun main(args: Array<String>) {

    val transactionRepository = TransactionRepositoryImpl()

    transactionRepository.getTransactions()
}