package com.nik.mornhouse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nik.mornhouse.data.entity.NumberFact

@Database(entities = [NumberFact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun numberFactDao(): NumberFactDao
}