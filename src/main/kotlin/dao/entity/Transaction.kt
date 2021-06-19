package dao.entity

import dao.entity.enum.Category
import dao.entity.enum.Operation
import dao.entity.enum.Status
import java.io.Serializable
import java.util.*

/**
 * This class contains the transaction entity.
 * @author Ivan Martinez Jimenez.
 */
data class Transaction(
    val amount: Double,
    val category: Category,
    val creation_date: Date,
    val description: String,
    val operation: Operation,
    val status: Status,
    val uuid: Int
) : Serializable
