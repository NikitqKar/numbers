package com.nik.mornhouse.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nik.mornhouse.data.entity.NumberFact
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberFactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(numberFact: NumberFact)

    @Query("SELECT * FROM number_facts ORDER BY number DESC")
    fun getAll(): Flow<List<NumberFact>>

}
