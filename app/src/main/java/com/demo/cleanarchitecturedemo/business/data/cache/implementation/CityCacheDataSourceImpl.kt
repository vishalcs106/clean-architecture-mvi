package com.demo.cleanarchitecturedemo.business.data.cache.implementation

import com.demo.cleanarchitecturedemo.business.data.cache.abstratction.CityCacheDataSource
import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.framework.datasource.cache.abstraction.CityDaoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityCacheDataSourceImpl
@Inject
constructor(private val cityDaoService: CityDaoService): CityCacheDataSource{
    override suspend fun addCity(city: City) {
        cityDaoService.addCity(city)
    }

    override suspend fun removeCity(id: String) {
        cityDaoService.removeCity(id)
    }

    override suspend fun getAllCities(): List<City> {
        return cityDaoService.getAllCities()
    }

}