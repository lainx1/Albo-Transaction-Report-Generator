package dao.entity.enum

import com.squareup.moshi.Json

enum class Operation {

    @Json(name = "in")
    IN,
    @Json(name = "out")
    OUT

}