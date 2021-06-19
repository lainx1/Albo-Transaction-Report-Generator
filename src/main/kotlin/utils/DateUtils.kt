package utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Month
import java.time.ZoneId
import java.util.*

/**
 * This class contains utils for date.
 * @author Ivan Martinez Jimenez.
 */
object DateUtils {

    var MEXICO_ZONE = "America/Mexico_City"
    var pattern = "dd/MM/yyyy"


    /**
     * Transform string to date given pattern.
     * @param stringDate a date in string.
     * @param pattern a valid pattern.
     * @return a Date given params.
     */
    fun stringToDate(stringDate: String, pattern : String) : Date {

        val dateFormat = SimpleDateFormat(pattern)
        with(dateFormat){
            this.timeZone = TimeZone.getTimeZone(ZoneId.of(DateUtils.MEXICO_ZONE))
        }

        return dateFormat.parse(stringDate)
    }

    /**
     * Transform date to string.
     * @param date to convert to string.
     * @return a string date.
     */
    fun stringToDate(date: Date) : String {

        val dateFormat = SimpleDateFormat(pattern)
        with(dateFormat){
            this.timeZone = TimeZone.getTimeZone(ZoneId.of(DateUtils.MEXICO_ZONE))
        }

        return dateFormat.format(date)
    }

    /**
     * Retrieve month given date.
     * @param date to get moth.
     * @return int month value.
     */
    fun getMonth(date: Date) : Month = date.toInstant().atZone(ZoneId.of(MEXICO_ZONE)).month
}