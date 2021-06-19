package dao.entity.enum.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import dao.entity.enum.Operation

/**
 * This class contains the operation adapter for category and Transaction Operation field.
 * @author Ivan Martinez Jimenez.
 */
class OperationAdapter {

    /**
     * Transform string to Operation enum.
     * @param jsonReader
     * @param delegate
     * @return operation enum.
     */
    @FromJson
    fun stringToOperation(jsonReader: JsonReader, delegate: JsonAdapter<Operation>): Operation? {
        val value = jsonReader.nextString()
        return if (value.startsWith("in")) Operation.IN else delegate.fromJsonValue(value)
    }
}