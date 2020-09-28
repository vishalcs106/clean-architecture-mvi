package com.demo.cleanarchitecturedemo.framework.datasource.cache.abstraction

import com.demo.cleanarchitecturedemo.business.domain.model.City

interface CityDaoService{
    suspend fun addCity(city: City)
    suspend fun removeCity(id: String)
    suspend fun getAllCities(): List<City>
}