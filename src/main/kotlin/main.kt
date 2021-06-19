import bo.TransactionBO

/**
 * This application create a report of transactions by months.
 * @author Ivan Martinez Jimenez.
 */
fun main(args: Array<String>) {

    val transactionsBO = TransactionBO()
    transactionsBO.generateReport()

}