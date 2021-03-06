package dao.entity

import com.squareup.moshi.Json
import dao.entity.enum.Category
import dao.entity.enum.Operation
import dao.entity.enum.Status
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

/**
 * This class contains the transaction entity.
 * @author Ivan Martinez Jimenez.
 */
data class Transaction(
    val amount: BigDecimal,
    val category: Category,
    @Json(name = "creation_date")
    val creationDate: Date,
    val description: String,
    val operation: Operation,
    val status: Status,
    val uuid: Int
) : Serializable
