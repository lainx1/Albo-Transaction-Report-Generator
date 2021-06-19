package dao.entity.enum

import com.squareup.moshi.Json

/**
 * This class contains the category enum.
 * @author Ivan Martinez Jimenez.
 */
enum class Category {

    @Json(name = "Alimentacion")
    ALIMENTATION,
    @Json(name = "Hogar")
    HOME,
    @Json(name = "Entretenimiento")
    ENTERTAINMENT,
    @Json(name = "Servicios")
    SERVICES,
    @Json(name = "Transferencias")
    TRANSFERS,
    @Json(name = "Retiros en cajero")
    WITHDRAWALS,
    @Json(name = "Otros")
    OTHERS,
    @Json(name = "Transporte")
    TRANSPORT;

}