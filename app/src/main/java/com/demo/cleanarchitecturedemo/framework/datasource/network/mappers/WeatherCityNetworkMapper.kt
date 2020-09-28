package com.demo.cleanarchitecturedemo.framework.datasource.network.mappers

import com.demo.cleanarchitecturedemo.business.domain.model.CityWeather
import com.demo.cleanarchitecturedemo.business.domain.util.EntityMapper
import com.demo.cleanarchitecturedemo.framework.datasource.network.model.Main
import com.demo.cleanarchitecturedemo.framework.datasource.network.model.Weather
import com.demo.cleanarchitecturedemo.framework.datasource.network.model.WeatherCityNetworkEntity

class WeatherCityNetworkMapper: EntityMapper<WeatherCityNetworkEntity, CityWeather> {
    override fun mapFromEntity(entity: WeatherCityNetworkEntity): CityWeather {
        return CityWeather(entity.id, entity.main.temp, entity.weather.main)
    }

    override fun mapToEntity(domainModel: CityWeather): WeatherCityNetworkEntity {
        return WeatherCityNetworkEntity(domainModel.id, 0L,
            Main(domainModel.temperature, 0.0, 0.0), Weather(domainModel.weather, ""))
    }
}