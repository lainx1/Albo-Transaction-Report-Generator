package dao.entity

import java.io.Serializable

/**
 * This class contains the transaction entity.
 * @author Ivan Martinez Jimenez.
 */
data class Transaction(
    val amount: Double,
    val category: String,
    val creation_date: String,
    val description: String,
    val operation: String,
    val status: String,
    val uuid: Int
) : Serializable
