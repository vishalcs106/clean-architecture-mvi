package com.demo.cleanarchitecturedemo.framework.datasource.network.implementation

import com.demo.cleanarchitecturedemo.framework.datasource.network.abstration.WeatherHourlyService
import com.demo.cleanarchitecturedemo.framework.datasource.network.model.WeatherHourlyResponse
import com.demo.cleanarchitecturedemo.framework.datasource.network.retrofit.Constants
import com.demo.cleanarchitecturedemo.framework.datasource.network.retrofit.WeatherHourlyApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherHourlyServiceImpl
@Inject
constructor(private val weatherHourlyApi: WeatherHourlyApi)
    : WeatherHourlyService {

    override suspend fun getWeatherHourly(city: String): WeatherHourlyResponse {
        return weatherHourlyApi.get(city, Constants.APP_ID)
    }
}