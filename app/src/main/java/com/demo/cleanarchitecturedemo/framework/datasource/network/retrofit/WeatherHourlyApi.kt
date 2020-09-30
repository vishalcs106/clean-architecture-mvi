package com.demo.cleanarchitecturedemo.framework.datasource.network.retrofit

import com.demo.cleanarchitecturedemo.framework.datasource.network.model.WeatherHourlyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherHourlyApi {
    @GET("data/2.5/forecast/hourly")
    suspend fun get(@Query("q") city: String, @Query("appid") appId: String ): WeatherHourlyResponse

}