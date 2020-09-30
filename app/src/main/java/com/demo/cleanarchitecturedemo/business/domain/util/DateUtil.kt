package com.demo.cleanarchitecturedemo.business.domain.util

import java.text.SimpleDateFormat
import javax.inject.Singleton

@Singleton
class DateUtil {
    fun getTimeFromDate(str: String): String {
        return str.substring(11, 16)
    }

    fun getDateString(str: String): String {
        return str.substring(0, 10)
    }
}