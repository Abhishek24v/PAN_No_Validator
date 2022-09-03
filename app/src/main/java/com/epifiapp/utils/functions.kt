package com.epifiapp.utils;

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val BIRTHDAY_FORMAT_PARSER: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
fun parseDateString(date: String?): Calendar {
    val calendar = Calendar.getInstance()
    BIRTHDAY_FORMAT_PARSER.isLenient = false
    try {
        calendar.time = BIRTHDAY_FORMAT_PARSER.parse(date)
    } catch (e: ParseException) {
    }
    return calendar
}
