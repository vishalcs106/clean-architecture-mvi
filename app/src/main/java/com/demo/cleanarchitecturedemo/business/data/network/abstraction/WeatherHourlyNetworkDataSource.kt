package com.demo.cleanarchitecturedemo.business.data.network.abstraction

import com.demo.cleanarchitecturedemo.business.domain.model.WeatherHourly

interface WeatherHourlyNetworkDataSource{
suspend fun getWeatherHourly(city: String): List<WeatherHourly>
}