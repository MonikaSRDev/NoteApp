package com.tutorial.noteapp.utilities

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromDate(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun fromTimeStamp(time: Long): Date{
        return Date(time)
    }
}