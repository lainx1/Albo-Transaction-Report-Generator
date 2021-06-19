package utils

import java.math.BigDecimal

/**
 * This class contains number utils
 * @author Ivan Martinez Jimenez.
 */
object NumberUtils {

    /**
     * Convert to certain decimals
     * @param number: the number to round.
     * @param totalDecimals: the total of decimals to round.
     * @param roundingMode: the BigDecimal rounding mode.
     * @return number rounded.
     */
    fun convertToDecimals(
        number: BigDecimal,
        totalDecimals: Int = 2,
        roundingMode: Int = BigDecimal.ROUND_HALF_EVEN
    ): BigDecimal {
        return number.setScale(totalDecimals, roundingMode);
    }

}