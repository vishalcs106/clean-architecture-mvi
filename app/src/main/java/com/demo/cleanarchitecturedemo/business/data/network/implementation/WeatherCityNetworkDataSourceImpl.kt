package com.demo.cleanarchitecturedemo.business.data.network.implementation

import com.demo.cleanarchitecturedemo.business.data.network.abstraction.WeatherCityNetworkDataSource
import com.demo.cleanarchitecturedemo.business.domain.model.CityWeather
import com.demo.cleanarchitecturedemo.framework.datasource.network.abstration.WeatherCityService
import com.demo.cleanarchitecturedemo.framework.datasource.network.mappers.WeatherCityNetworkMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherCityNetworkDataSourceImpl
@Inject
constructor(private val weatherCityService: WeatherCityService,
private val weatherCityNetworkMapper: WeatherCityNetworkMapper): WeatherCityNetworkDataSource{
    override suspend fun getCityWeather(city: String): CityWeather {
        return weatherCityNetworkMapper.mapFromEntity(weatherCityService.getCityWeather(city))
    }
}