package dao.entity.enum

import com.squareup.moshi.Json

/**
 * This class contains the status enum.
 * @author Ivan Martinez Jimenez.
 */
enum class Status {

    @Json(name = "rejected")
    REJECTED,
    @Json(name = "pending")
    PENDING,
    @Json(name = "done")
    DONE

}