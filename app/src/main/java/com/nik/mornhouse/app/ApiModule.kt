package com.nik.mornhouse.app

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.nik.mornhouse.data.database.AppDatabase
import com.nik.mornhouse.data.database.NumberFactDao
import com.nik.mornhouse.data.web.NumberRepository
import com.nik.mornhouse.data.web.NumbersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://numbersapi.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
    @Provides
    fun provideApi(retrofit: Retrofit): NumbersApi = retrofit.create(NumbersApi::class.java)

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "number_facts_database"
        ).build()
    }

    @Provides
    fun provideNumberFactDao(database: AppDatabase): NumberFactDao {
        return database.numberFactDao()
    }

    @Provides
    fun provideRepository(api: NumbersApi, numberFactDao: NumberFactDao): NumberRepository {
        Log.d("TAG1", "provideRepository")
        return NumberRepository(api, numberFactDao)
    }

}
