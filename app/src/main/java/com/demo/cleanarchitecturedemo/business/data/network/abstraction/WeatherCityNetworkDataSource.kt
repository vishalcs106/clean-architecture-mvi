package com.demo.cleanarchitecturedemo.business.data.network.abstraction

import com.demo.cleanarchitecturedemo.business.domain.model.CityWeather

interface WeatherCityNetworkDataSource {
    suspend fun getCityWeather(city: String): CityWeather
}