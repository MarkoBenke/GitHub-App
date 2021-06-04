package com.marko.githubapp.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
const val LOCAL_DATE_FORMAT = "dd.MM.yyyy HH:mm"

fun parseDate(selectedDate: String): String {
    val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return try {
        val dateFromPattern = formatter.parse(selectedDate)
        val sdf = SimpleDateFormat(LOCAL_DATE_FORMAT, Locale.getDefault())
        sdf.format(dateFromPattern!!) ?: ""
    } catch (e: ParseException) {
        ""
    }
}