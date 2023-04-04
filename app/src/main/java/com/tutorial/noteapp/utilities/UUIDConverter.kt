package com.tutorial.noteapp.utilities

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String{
        return uuid.toString()
    }

    @TypeConverter
    fun fromStringUUID(string: String): UUID{
        return UUID.fromString(string)
    }
}