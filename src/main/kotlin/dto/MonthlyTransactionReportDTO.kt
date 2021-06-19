package dto

import utils.NumberUtils
import java.math.BigDecimal
import java.util.*

/**
 * This class contains the representation of monthly transaction report.
 * @author Ivan Martinez Jimenez.
 */
class MonthlyTransactionReportDTO private constructor(
    val month: String?,
    val pendingTransactions: Int?,
    val rejectedTransactions: Int?,
    val incomeAmount: BigDecimal?,
    val outcomeAmount: BigDecimal?,
    val categoryReports: List<CategoryReportDTO>?
) {

    data class Builder(
        var month: String? = null,
        var pendingTransactions: Int? = null,
        var rejectedTransactions: Int? = null,
        var incomeAmount: BigDecimal? = null,
        var outcomeAmount: BigDecimal? = null,
        var categoryReports: List<CategoryReportDTO>? = null
    ) {

        fun month(month: String) = apply { this.month = month }
        fun pendingTransactions(pendingTransactions: Int) = apply { this.pendingTransactions = pendingTransactions }
        fun rejectedTransactions(rejectedTransactions: Int) = apply { this.rejectedTransactions = rejectedTransactions }
        fun incomeAmount(incomeAmount: BigDecimal) = apply { this.incomeAmount = incomeAmount }
        fun outcomeAmount(outcomeAmount: BigDecimal) = apply { this.outcomeAmount = outcomeAmount }
        fun categoryReports(categoryReports: List<CategoryReportDTO>) = apply { this.categoryReports = categoryReports }

        fun build() = MonthlyTransactionReportDTO(
            month = month,
            pendingTransactions = pendingTransactions,
            rejectedTransactions = rejectedTransactions,
            incomeAmount = incomeAmount,
            outcomeAmount = outcomeAmount,
            categoryReports = categoryReports
        )
    }

    override fun toString(): String {

        var response = "${month!!.capitalize(Locale("es", "MX"))}:\n" +
                "\t$pendingTransactions transacciones pendientes\n" +
                "\t$rejectedTransactions bloqueadas\n\n" +
                "\t$${NumberUtils.convertToDecimals(number = incomeAmount ?: BigDecimal.ZERO)} ingresos\n\n" +
                "\t$${NumberUtils.convertToDecimals(number = outcomeAmount ?: BigDecimal.ZERO)} gastos\n\n"

        if (categoryReports != null) {
            for (categoryReport in categoryReports) {
                response += "\t\t$categoryReport\n"
            }
        }


        return response
    }

}