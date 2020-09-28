package com.demo.cleanarchitecturedemo.business.data.cache.abstratction

import com.demo.cleanarchitecturedemo.business.domain.model.City

interface CityCacheDataSource {
    suspend fun addCity(city: City)
    suspend fun removeCity(id: String)
    suspend fun getAllCities(): List<City>
}