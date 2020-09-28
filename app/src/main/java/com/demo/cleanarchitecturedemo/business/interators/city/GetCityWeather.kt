package com.demo.cleanarchitecturedemo.business.interators.city

import com.demo.cleanarchitecturedemo.business.data.network.ApiResponseHandler
import com.demo.cleanarchitecturedemo.business.data.network.abstraction.WeatherCityNetworkDataSource
import com.demo.cleanarchitecturedemo.business.data.util.safeApiCall
import com.demo.cleanarchitecturedemo.business.domain.model.CityWeather
import com.demo.cleanarchitecturedemo.business.domain.state.DataState
import com.demo.cleanarchitecturedemo.business.domain.state.StateEvent
import com.demo.cleanarchitecturedemo.business.domain.state.ViewState
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityListViewState
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityWeatherViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow

class GetCityWeather(
    private val weatherCityNetworkDataSource: WeatherCityNetworkDataSource
) {

    suspend fun getCityWeather(cityName: String, stateEvent: StateEvent): Flow<DataState<CityListViewState>?> {
        val networkResult = safeApiCall(IO){
            weatherCityNetworkDataSource.getCityWeather(cityName)
        }

        val response = object: ApiResponseHandler<CityWeather, CityWeather>(
            response = networkResult,
            stateEvent = null
        ){
            override suspend fun handleSuccess(resultObj: CityWeather): DataState<CityWeather>? {
                return DataState.data(
                    response = null,
                    data = resultObj,
                    stateEvent = null
                )
            }
        }.getResult()


        emit(response)




    }

}