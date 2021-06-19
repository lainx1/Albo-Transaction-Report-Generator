package dao.entity.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import dao.entity.enum.Status

/**
 * This class contains the operation adapter for status and Transaction status field.
 * @author Ivan Martinez Jimenez.
 */
class StatusAdapter {

    /**
     * Transform string to Status enum.
     * @param jsonReader
     * @param delegate
     * @return status enum.
     */
    @FromJson
    fun stringToStatus(jsonReader: JsonReader, delegate: JsonAdapter<Status>): Status? {
        val value = jsonReader.nextString()
        return delegate.fromJsonValue(value)
    }
}