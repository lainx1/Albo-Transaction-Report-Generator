package dao.entity.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal
import java.math.MathContext

/**
 * This class contains the BigDecimal Adapter for amount Transaction field.
 * @author Ivan Martinez Jimenez.
 */
class BigDecimalAdapter {

    /**
     * Convert double to big decimal.
     *
     * @param bigDecimal to convert to double.
     * @return double.
     */
    @ToJson
    fun bigDecimalToDouble(bigDecimal: BigDecimal) : Double = bigDecimal.toDouble()

    /**
     * Convert double to big decimal.
     *
     * @param double to convert to big decimal.
     * @return big decimal.
     */
    @FromJson
    fun doubleToBigDecimal(double: Double) : BigDecimal = BigDecimal(double, MathContext.DECIMAL64)

}