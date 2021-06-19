package dao.entity.enum.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import dao.entity.enum.Category

/**
 * This class contains the category adapter for category and Transaction category field.
 * @author Ivan Martinez Jimenez.
 */
class CategoryAdapter {

    /**
     * Transform string to Category enum.
     * @param jsonReader
     * @param delegate
     * @return category enum.
     */
    @FromJson
    fun stringToCategory(jsonReader: JsonReader, delegate: JsonAdapter<Category>): Category? {
        val value = jsonReader.nextString()
        return if (value.startsWith("Alimentacion")) Category.ALIMENTATION else delegate.fromJsonValue(value)
    }
}