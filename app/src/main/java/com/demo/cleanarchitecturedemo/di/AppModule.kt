package com.demo.cleanarchitecturedemo.di

import androidx.room.Room
import com.demo.cleanarchitecturedemo.business.data.cache.abstratction.CityCacheDataSource
import com.demo.cleanarchitecturedemo.business.data.cache.implementation.CityCacheDataSourceImpl
import com.demo.cleanarchitecturedemo.business.data.network.abstraction.WeatherCityNetworkDataSource
import com.demo.cleanarchitecturedemo.business.data.network.abstraction.WeatherHourlyNetworkDataSource
import com.demo.cleanarchitecturedemo.business.data.network.implementation.WeatherCityNetworkDataSourceImpl
import com.demo.cleanarchitecturedemo.business.data.network.implementation.WeatherHourlyNetworkDataSourceImpl
import com.demo.cleanarchitecturedemo.business.domain.model.WeatherHourly
import com.demo.cleanarchitecturedemo.business.domain.util.DateUtil
import com.demo.cleanarchitecturedemo.business.domain.util.EntityMapper
import com.demo.cleanarchitecturedemo.business.interators.CityListInteractors
import com.demo.cleanarchitecturedemo.business.interators.WeatherDetailsInteractor
import com.demo.cleanarchitecturedemo.business.interators.city.AddCity
import com.demo.cleanarchitecturedemo.business.interators.city.GetAllCities
import com.demo.cleanarchitecturedemo.business.interators.city.GetCityWeather
import com.demo.cleanarchitecturedemo.business.interators.city.RemoveCity
import com.demo.cleanarchitecturedemo.business.interators.weather.GetHourlyWeather
import com.demo.cleanarchitecturedemo.framework.datasource.cache.abstraction.CityDaoService
import com.demo.cleanarchitecturedemo.framework.datasource.cache.database.AppDatabase
import com.demo.cleanarchitecturedemo.framework.datasource.cache.database.CityDao
import com.demo.cleanarchitecturedemo.framework.datasource.cache.implementation.CityDaoServiceImpl
import com.demo.cleanarchitecturedemo.framework.datasource.cache.mappers.CacheMapper
import com.demo.cleanarchitecturedemo.framework.datasource.network.abstration.WeatherCityService
import com.demo.cleanarchitecturedemo.framework.datasource.network.abstration.WeatherHourlyService
import com.demo.cleanarchitecturedemo.framework.datasource.network.implementation.WeatherCityServiceImpl
import com.demo.cleanarchitecturedemo.framework.datasource.network.implementation.WeatherHourlyServiceImpl
import com.demo.cleanarchitecturedemo.framework.datasource.network.mappers.WeatherCityNetworkMapper
import com.demo.cleanarchitecturedemo.framework.datasource.network.mappers.WeatherHourlyNetworkMapper
import com.demo.cleanarchitecturedemo.framework.datasource.network.model.WeatherHourlyNetworkEntity
import com.demo.cleanarchitecturedemo.framework.datasource.network.retrofit.WeatherCityApi
import com.demo.cleanarchitecturedemo.framework.datasource.network.retrofit.WeatherHourlyApi
import com.demo.cleanarchitecturedemo.framework.presentation.ThisApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@ExperimentalCoroutinesApi
@FlowPreview
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDb(app: ThisApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCityDAO(appDatabase: AppDatabase): CityDao {
        return appDatabase.cityDao()
    }


    @Singleton
    @Provides
    fun provideNetworkMapper(): EntityMapper<WeatherHourlyNetworkEntity, WeatherHourly> {
        return WeatherHourlyNetworkMapper()
    }


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    @Named("retrofit_2")
    fun provideRetrofit2(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://samples.openweathermap.org/")
            .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideWeatherHourlyService(@Named("retrofit_2")retrofit: Retrofit.Builder): WeatherHourlyApi {
        return retrofit
            .build()
            .create(WeatherHourlyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherCityService(retrofit: Retrofit.Builder): WeatherCityApi {
        return retrofit
            .build()
            .create(WeatherCityApi::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCityCacheMapper(): CacheMapper {
        return CacheMapper()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCityCacheDataSource(
        cityDaoService: CityDaoService
    ): CityCacheDataSource {
        return CityCacheDataSourceImpl(cityDaoService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWeatherCityNetworkMapper(): WeatherCityNetworkMapper {
        return WeatherCityNetworkMapper()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWeatherHourlyNetworkMapper(): WeatherHourlyNetworkMapper {
        return WeatherHourlyNetworkMapper()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCityDaoService(
        cityDao: CityDao,
        noteEntityMapper: CacheMapper
    ): CityDaoService{
        return CityDaoServiceImpl(cityDao, noteEntityMapper)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun providesWeatherCityService(weatherCityApi: WeatherCityApi): WeatherCityService{
        return WeatherCityServiceImpl(weatherCityApi)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun providesWeatherHourlyService(weatherHourlyApi: WeatherHourlyApi): WeatherHourlyService{
        return WeatherHourlyServiceImpl(weatherHourlyApi)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWeatherCityNetworkDataSource(
        weatherCityService: WeatherCityService,
        weatherCityNetworkMapper: WeatherCityNetworkMapper
    ): WeatherCityNetworkDataSource {
        return WeatherCityNetworkDataSourceImpl(weatherCityService, weatherCityNetworkMapper)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWeatherHourlyNetworkDataSource(
        weatherHourlyService: WeatherHourlyService,
        weatherHourlyNetworkMapper: WeatherHourlyNetworkMapper
    ): WeatherHourlyNetworkDataSource {
        return WeatherHourlyNetworkDataSourceImpl(weatherHourlyService, weatherHourlyNetworkMapper)
    }



    @Singleton
    @Provides
    fun providesCityListInteractors(cityCacheDataSource: CityCacheDataSource,
    cityNetworkDataSource: WeatherCityNetworkDataSource): CityListInteractors{
        return CityListInteractors(
            AddCity(cityCacheDataSource),
            RemoveCity(cityCacheDataSource),
            GetCityWeather(cityNetworkDataSource),
            GetAllCities(cityCacheDataSource)
        )
    }

    @Singleton
    @Provides
    fun providesWeatherDetailsInteractor(weatherHourlyNetworkDataSource:
                                         WeatherHourlyNetworkDataSource): WeatherDetailsInteractor{
        return WeatherDetailsInteractor(
            GetHourlyWeather(weatherHourlyNetworkDataSource)
        )
    }

    @Singleton
    @Provides
    fun providesDataUtil(): DateUtil{
        return DateUtil()
    }





}