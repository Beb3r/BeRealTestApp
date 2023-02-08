package com.gromo.persistence.converter

import android.text.TextUtils
import androidx.room.TypeConverter

internal class SimpleListConverter {

    @TypeConverter
    fun listToString(input: List<String>?): String? =
        if (input == null) {
            null
        } else {
            TextUtils.join(",", input)
        }


    @TypeConverter
    fun stringToList(input: String?): List<String>? =
        if (input == null) {
            null
        } else {
            TextUtils
                .split(input, ",")
                .toList()
        }

}