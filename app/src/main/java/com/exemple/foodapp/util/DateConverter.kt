package com.exemple.foodapp.util

import java.util.*

object DateConverter {


    fun longToDate(long: Long): Date {
        return Date(long)
    }

    fun dateToLong(date: Date): Long {
        return date.time / 1000
    }


    fun createTimestamp(): Date{
        return Date()
    }


}