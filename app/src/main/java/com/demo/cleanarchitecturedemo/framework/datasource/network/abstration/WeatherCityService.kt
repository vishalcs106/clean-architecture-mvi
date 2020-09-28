package com.demo.cleanarchitecturedemo.framework.datasource.network.abstration

import com.demo.cleanarchitecturedemo.framework.datasource.network.model.WeatherCityNetworkEntity

interface WeatherCityService {
    suspend fun getCityWeather(city: String): WeatherCityNetworkEntity
}