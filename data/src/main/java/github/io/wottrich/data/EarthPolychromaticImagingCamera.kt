package github.io.wottrich.data

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.InvalidPropertiesFormatException

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 13/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

data class EarthPolychromaticImagingCamera(
    val identifier: String,
    val caption: String,
    val image: String,
    val date: String
) {

    fun getImageUrl(): String {
        val formatDate = getFormatDate()
        return "https://epic.gsfc.nasa.gov/archive/natural/$formatDate/png/$image.png"
    }

    private fun getFormatDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate: Date? = formatter.parse(date)
        val calendarInstance = Calendar.getInstance()
        calendarInstance.time =
            formattedDate ?: throw InvalidPropertiesFormatException(FormatDateExceptionMessage)
        val year = calendarInstance.get(Calendar.YEAR)
        val day = calendarInstance.get(Calendar.DAY_OF_MONTH)
        val month = calendarInstance.getMonthCorrectly()
        val returnDate = "$year/$month/$day"
        return returnDate
    }

    private fun Calendar.getMonthCorrectly(): String {
        val month = (this.get(Calendar.MONTH) + 1).toString()
        return if (month.length == 1) {
            "0$month"
        } else {
            month
        }
    }

    companion object {
        private const val FormatDateExceptionMessage = "Invalid date format"
    }

}
