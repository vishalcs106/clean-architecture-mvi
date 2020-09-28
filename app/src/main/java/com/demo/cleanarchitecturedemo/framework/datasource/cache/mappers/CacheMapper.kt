package com.demo.cleanarchitecturedemo.framework.datasource.cache.mappers

import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.business.domain.util.EntityMapper
import com.demo.cleanarchitecturedemo.framework.datasource.cache.model.CityCacheEntity

class CacheMapper: EntityMapper<CityCacheEntity, City> {

    fun entityListToCityList(entities: List<CityCacheEntity>): List<City>{
        val list: ArrayList<City> = ArrayList()
        for(entity in entities){
            list.add(mapFromEntity(entity))
        }
        return list
    }

    override fun mapFromEntity(entity: CityCacheEntity): City {
        return City(entity.id, entity.name, false)
    }

    override fun mapToEntity(domainModel: City): CityCacheEntity {
        return CityCacheEntity(domainModel.id, domainModel.name)
    }
}