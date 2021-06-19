import bo.TransactionBO

/**
 * This application create a report of transactions by months.
 * @author Ivan Martinez Jimenez.
 */
fun main(args: Array<String>) {
    println("********************************************************************************")
    println("Prueba albo.")
    println("Ivan Martinez Jimenez.")
    println("********************************************************************************")
    println("Reporte de transaciones.")
    println("********************************************************************************")
    val transactionsBO = TransactionBO()
    transactionsBO.generateReport()

}