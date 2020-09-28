package com.demo.cleanarchitecturedemo.business.data.network.implementation

import com.demo.cleanarchitecturedemo.business.data.network.abstraction.WeatherHourlyNetworkDataSource
import com.demo.cleanarchitecturedemo.business.domain.model.WeatherHourly
import com.demo.cleanarchitecturedemo.framework.datasource.network.abstration.WeatherHourlyService
import com.demo.cleanarchitecturedemo.framework.datasource.network.mappers.WeatherHourlyNetworkMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherHourlyNetworkDataSourceImpl
@Inject
constructor(
    private val weatherHourlyService: WeatherHourlyService,
    private val weatherHourlyNetworkMapper: WeatherHourlyNetworkMapper
): WeatherHourlyNetworkDataSource {
    override suspend fun getWeatherHourly(city: String): List<WeatherHourly> {
        val weatherHourly = weatherHourlyService.getWeatherHourly(city)
        weatherHourly.list?.let{
            return weatherHourlyNetworkMapper.entityListToWeatherHourlyList(it)
        }
        return ArrayList<WeatherHourly>()
    }
}