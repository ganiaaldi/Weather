package com.aldi.ganiaweather.etc

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
interface Extensions {
    @RequiresApi(Build.VERSION_CODES.O)
    fun changeFormatDate(date : String): String {
        val a = date!!.split("\\s".toRegex())[0]
        val b = date!!.split("\\s".toRegex())[1]
        var parsedDate = LocalDate.parse("${a}", DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        var formatter = DateTimeFormatter.ofPattern("EEE,dd MM yyyy")
        var formattedDate = parsedDate.format(formatter)
        return formattedDate
    }

    fun getTime(date : String): String {
        val a = date!!.split("\\s".toRegex())[0]
        val b = date!!.split("\\s".toRegex())[1]
        return b
    }
}
