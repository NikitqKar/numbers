package com.nik.mornhouse.data.web

import android.util.Log
import com.nik.mornhouse.data.database.NumberFactDao
import com.nik.mornhouse.data.entity.NumberFact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow


class NumberRepository(private val api: NumbersApi, private val numberFactDao: NumberFactDao) {
    suspend fun getNumberFact(number: String): String {
        val response = api.getNumber(number)
        if (response.isSuccessful) {
            val text = response.body() ?: throw Exception("Response body is null")
            val numberFact = NumberFact(number.toInt(), text)
            numberFactDao.insert(numberFact)
            Log.d("Nik", "insert in database")

            return text
        } else {
            throw Exception("API call failed")
        }
    }

    fun getNumberFacts(): Flow<List<NumberFact>> {
        return numberFactDao.getAll()
    }
}