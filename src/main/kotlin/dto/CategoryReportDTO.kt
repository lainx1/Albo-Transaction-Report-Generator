package dto

import dao.entity.enum.Category
import utils.NumberUtils
import java.math.BigDecimal
import java.util.*

/**
 * This class contains the category report.
 * @author Ivan Martinez Jimenez.
 */
class CategoryReportDTO private constructor(

    val category : Category?,
    val amount: BigDecimal?

){
    data class Builder(
        var category : Category ?= null,
        var amount : BigDecimal ?= null
    ){

        fun category(category: Category) = apply { this.category= category }
        fun amount(amount: BigDecimal) = apply { this.amount = amount }

        fun build() = CategoryReportDTO(
            category = category,
            amount = amount
        )
    }

    override fun toString(): String = "${category!!.value.capitalize(Locale("es", "MX"))} \t\t ${NumberUtils.convertToDecimals(number = amount!!)}"
}