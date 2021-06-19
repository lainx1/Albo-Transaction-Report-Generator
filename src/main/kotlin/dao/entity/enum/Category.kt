package dao.entity.enum

import com.squareup.moshi.Json

/**
 * This class contains the category enum.
 * @author Ivan Martinez Jimenez.
 */
enum class Category(val value : String) {

    @Json(name = "Alimentacion")
    ALIMENTATION("Alimentacion"),
    @Json(name = "Hogar")
    HOME("Hogar"),
    @Json(name = "Entretenimiento")
    ENTERTAINMENT("Entretenimiento"),
    @Json(name = "Servicios")
    SERVICES("Servicios"),
    @Json(name = "Transferencias")
    TRANSFERS("Transferencias"),
    @Json(name = "Retiros en cajero")
    WITHDRAWALS("Retiros en cajero"),
    @Json(name = "Otros")
    OTHERS("Otros"),
    @Json(name = "Transporte")
    TRANSPORT("Transporte");

}