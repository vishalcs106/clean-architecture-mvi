package com.demo.cleanarchitecturedemo.di

import androidx.room.Room
import com.demo.cleanarchitecturedemo.business.domain.model.WeatherHourly
import com.demo.cleanarchitecturedemo.business.domain.util.EntityMapper
import com.demo.cleanarchitecturedemo.framework.datasource.cache.database.AppDatabase
import com.demo.cleanarchitecturedemo.framework.datasource.network.mappers.WeatherHourlyNetworkMapper
import com.demo.cleanarchitecturedemo.framework.datasource.network.model.WeatherHourlyNetworkEntity
import com.demo.cleanarchitecturedemo.framework.datasource.network.retrofit.WeatherCityApi
import com.demo.cleanarchitecturedemo.framework.datasource.network.retrofit.WeatherHourlyApi
import com.demo.cleanarchitecturedemo.framework.presentation.ThisApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@ExperimentalCoroutinesApi
@FlowPreview
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDb(app: ThisApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideNetworkMapper(): EntityMapper<WeatherHourlyNetworkEntity, WeatherHourly> {
        return WeatherHourlyNetworkMapper()
    }


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideWeatherHourlyService(retrofit: Retrofit.Builder): WeatherHourlyApi {
        return retrofit
            .build()
            .create(WeatherHourlyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherCityService(retrofit: Retrofit.Builder): WeatherCityApi {
        return retrofit
            .build()
            .create(WeatherCityApi::class.java)
    }


}