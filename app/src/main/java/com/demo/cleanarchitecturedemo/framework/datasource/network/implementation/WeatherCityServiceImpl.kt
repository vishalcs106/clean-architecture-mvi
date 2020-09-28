package com.demo.cleanarchitecturedemo.framework.datasource.network.implementation

import com.demo.cleanarchitecturedemo.framework.datasource.network.abstration.WeatherCityService
import com.demo.cleanarchitecturedemo.framework.datasource.network.model.WeatherCityNetworkEntity
import com.demo.cleanarchitecturedemo.framework.datasource.network.retrofit.Constants
import com.demo.cleanarchitecturedemo.framework.datasource.network.retrofit.WeatherCityApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherCityServiceImpl
    @Inject
    constructor(private val weatherCityApi: WeatherCityApi): WeatherCityService{
    override suspend fun getCityWeather(city: String): WeatherCityNetworkEntity {
        return weatherCityApi.get(city, Constants.APP_ID)
    }

}