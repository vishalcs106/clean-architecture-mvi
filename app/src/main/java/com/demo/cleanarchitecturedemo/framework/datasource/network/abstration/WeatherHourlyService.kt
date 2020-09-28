package com.demo.cleanarchitecturedemo.framework.datasource.network.abstration

import com.demo.cleanarchitecturedemo.framework.datasource.network.model.WeatherHourlyResponse

interface WeatherHourlyService {
    suspend fun getWeatherHourly(city: String): WeatherHourlyResponse
}