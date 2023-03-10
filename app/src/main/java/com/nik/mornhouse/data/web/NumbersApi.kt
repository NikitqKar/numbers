package com.nik.mornhouse.data.web

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
interface NumbersApi {
    @GET("{number}")
    suspend fun getNumber(@Path("number") number: String): Response<String>
}