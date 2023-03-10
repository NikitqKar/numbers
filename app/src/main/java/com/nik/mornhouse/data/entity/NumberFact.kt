package com.nik.mornhouse.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number_facts")
data class NumberFact(
    @PrimaryKey
    val number: Int,
    val text: String
)