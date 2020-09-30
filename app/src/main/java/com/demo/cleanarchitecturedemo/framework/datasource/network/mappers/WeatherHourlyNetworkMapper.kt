package com.demo.cleanarchitecturedemo.framework.datasource.network.mappers

import com.demo.cleanarchitecturedemo.business.domain.model.WeatherHourly
import com.demo.cleanarchitecturedemo.business.domain.util.EntityMapper
import com.demo.cleanarchitecturedemo.framework.datasource.network.model.Main
import com.demo.cleanarchitecturedemo.framework.datasource.network.model.Weather
import com.demo.cleanarchitecturedemo.framework.datasource.network.model.WeatherHourlyNetworkEntity


class WeatherHourlyNetworkMapper: EntityMapper<WeatherHourlyNetworkEntity, WeatherHourly> {

    fun entityListToWeatherHourlyList(entities: List<WeatherHourlyNetworkEntity>): List<WeatherHourly>{
        val list: ArrayList<WeatherHourly> = ArrayList()
        for(entity in entities){
            list.add(mapFromEntity(entity))
        }
        return list
    }

    override fun mapFromEntity(entity: WeatherHourlyNetworkEntity): WeatherHourly {
        return WeatherHourly(entity.main.temp, entity.main.tempMin, entity.main.tempMax,
            entity.weather[0].main, entity.weather[0].description, entity.dtString, entity.dt)
    }

    override fun mapToEntity(domainModel: WeatherHourly): WeatherHourlyNetworkEntity {
        return WeatherHourlyNetworkEntity(domainModel.timestamp,
            Main(domainModel.temp, domainModel.tempMin, domainModel.tempMax),
        arrayListOf(Weather(domainModel.weather, domainModel.weatherDesc)), domainModel.dateTime)
    }
}