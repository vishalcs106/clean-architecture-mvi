package com.demo.cleanarchitecturedemo.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.cleanarchitecturedemo.framework.datasource.cache.model.CityCacheEntity

@Database(entities = [CityCacheEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cityDao(): CityDao

    companion object{
        val DATABASE_NAME: String = "app_db"
    }

}