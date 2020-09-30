package com.demo.cleanarchitecturedemo.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.cleanarchitecturedemo.framework.datasource.cache.model.CityCacheEntity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(cityCache: CityCacheEntity)

    @Query("DELETE FROM cities WHERE id = :id")
    suspend fun deleteCity(id: String)

    @Query("SELECT * FROM cities")
    suspend fun getAllCities(): List<CityCacheEntity>

}