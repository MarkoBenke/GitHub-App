package com.marko.githubapp.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val GITHUB_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
const val LOCAL_DATE_FORMAT = "dd.MM.yyyy HH:mm"

/**
 * Parses date from github's date format to out local date format
 *
 * @param selectedDate date that needs to be parsed
 *
 * @return date in local date format
 */
fun parseDate(selectedDate: String): String {
    val formatter = SimpleDateFormat(GITHUB_DATE_FORMAT, Locale.getDefault())
    return try {
        val dateFromPattern = formatter.parse(selectedDate)
        val sdf = SimpleDateFormat(LOCAL_DATE_FORMAT, Locale.getDefault())
        sdf.format(dateFromPattern!!) ?: ""
    } catch (e: ParseException) {
        ""
    }
}