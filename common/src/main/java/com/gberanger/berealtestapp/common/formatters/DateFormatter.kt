package com.gberanger.berealtestapp.common.formatters

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class DateFormatter private constructor(
    pattern: String,
    locale: Locale = Locale.US,
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
) : SimpleDateFormat(pattern, locale) {

    companion object {
        private const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
        private const val DATE_HOUR_MINUTE_SECOND_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

        fun create(pattern: String): DateFormatter = DateFormatter(pattern)

        fun toDate(
            date: String?,
            defaultValue: Date,
            pattern: String = DATE_HOUR_MINUTE_SECOND_FORMAT
        ): Date =
            if (date == null) {
                defaultValue
            } else {
                try {
                    DateFormatter(pattern = pattern).parse(date)
                } catch (e: ParseException) {
                    println("DateFormatter | Error while parsing date: $date, trying with default format")
                    tryDefaultDateFormat(date, defaultValue)
                }
            }

        private fun tryDefaultDateFormat(date: String, defaultValue: Date): Date =
            try {
                DateFormatter(pattern = DEFAULT_DATE_FORMAT).parse(date)
            } catch (e: ParseException) {
                println("DateFormatter | Error while parsing date: $date with default format")
                defaultValue
            }

        fun fromDate(date: Date, pattern: String = DEFAULT_DATE_FORMAT): String =
            DateFormatter(pattern = pattern).format(date)
    }

    init {
        this.isLenient = false
        this.timeZone = timeZone
    }
}
