package dao.entity.enum.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import utils.DateUtils
import java.util.*

/**
 * This class contains date adapter of Transaction creation_date field.
 * @author Ivan Martinez Jimenez.
 */
class DateAdapter {


    /**
     * Adapter from date to string.
     * @return date in format dd/MM/yyyy.
     */
    @ToJson
    fun dateToString(date: Date) : String = DateUtils.stringToDate(date = date)


    /**
     * Adapter from string in format dd/MM/yyyy to date.
     * @return date.
     */
    @FromJson
    fun stringToDate(stringDate: String) : Date = DateUtils.stringToDate(stringDate = stringDate, pattern = DateUtils.pattern)

}