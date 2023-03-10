package com.nik.mornhouse.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "number_facts")
data class NumberFact(
    @PrimaryKey
    val number: Int,
    val text: String
) : Parcelable