package com.demo.cleanarchitecturedemo.framework.datasource.cache.implementation

import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.framework.datasource.cache.abstraction.CityDaoService
import com.demo.cleanarchitecturedemo.framework.datasource.cache.database.CityDao
import com.demo.cleanarchitecturedemo.framework.datasource.cache.mappers.CacheMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityDaoServiceImpl
@Inject
constructor(
    private val cityDao: CityDao,
    private val cacheMapper: CacheMapper
): CityDaoService{
    override suspend fun addCity(city: City) {
        cityDao.addCity(cacheMapper.mapToEntity(city))
    }

    override suspend fun removeCity(id: String) {
        cityDao.deleteCity(id)
    }

    override suspend fun getAllCities(): List<City> {
        return cacheMapper.entityListToCityList(cityDao.getAllCities())
    }

}