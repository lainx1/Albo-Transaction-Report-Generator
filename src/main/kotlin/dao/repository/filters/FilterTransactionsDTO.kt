package dao.repository.filters

import dao.entity.enum.Category
import dao.entity.enum.Operation
import dao.entity.enum.Status
import java.time.Month


/**
 * This class contains the filter for transactions.
 */
class FilterTransactionsDTO private constructor(
    val status: Status?,
    val operation: Operation?,
    val month: Month?,
    val category: Category?
){

    data class Builder(
        var status: Status ?= null,
        var operation: Operation ?= null,
        var month: Month ?= null,
        var category: Category ?= null
    ){

        fun status(status: Status) = apply { this.status = status }
        fun operation(operation: Operation) = apply { this.operation = operation }
        fun month(month: Month) = apply { this.month = month }
        fun category(category: Category) = apply { this.category = category }

        fun build() = FilterTransactionsDTO(
            status = status,
            operation = operation,
            month = month,
            category = category
        )
    }

}