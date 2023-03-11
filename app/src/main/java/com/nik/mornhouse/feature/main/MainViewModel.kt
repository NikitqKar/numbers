package com.nik.mornhouse.feature.main

import android.util.Log
import androidx.lifecycle.*
import com.nik.mornhouse.data.entity.NumberFact
import com.nik.mornhouse.data.web.NumberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NumberRepository
) : ViewModel() {
init {
    Log.d("MainViewModel", "ViewModelCreated")
}
    private val _factTextLiveData = MutableLiveData<String>()
    val factTextLiveData: LiveData<String> = _factTextLiveData

    val itemsLiveData: LiveData<List<NumberFact>> = repository.getNumberFacts().asLiveData()

    suspend fun getNumberFact(number: Long): String {
        Log.d("Nik", "getNumberFact called with number: $number")
        val response = repository.getNumberFact(number.toString())
        return response
    }
    fun setFactText(fact: String) {
        Log.d("Nik", "setFactText called with fact: $fact")

        _factTextLiveData.value = fact
    }
}