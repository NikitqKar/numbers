package com.nik.mornhouse.feature.main

import android.util.Log
import androidx.lifecycle.*
import com.nik.mornhouse.data.entity.NumberFact
import com.nik.mornhouse.data.web.NumberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NumberRepository
) : ViewModel() {
    init {
        Log.d("TAG1", "constructor")
    }
    var factText: String = ""

    val itemsLiveData: LiveData<List<NumberFact>> = repository.getNumberFacts().asLiveData()

    suspend fun getNumberFact(number: Int): String {
        val response = repository.getNumberFact(number.toString())
        return response
    }
}