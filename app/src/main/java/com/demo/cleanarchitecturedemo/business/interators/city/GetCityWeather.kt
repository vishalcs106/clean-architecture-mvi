package com.demo.cleanarchitecturedemo.business.interators.city

import com.demo.cleanarchitecturedemo.business.data.network.ApiResponseHandler
import com.demo.cleanarchitecturedemo.business.data.network.abstraction.WeatherCityNetworkDataSource
import com.demo.cleanarchitecturedemo.business.data.util.safeApiCall
import com.demo.cleanarchitecturedemo.business.domain.model.CityWeather
import com.demo.cleanarchitecturedemo.business.domain.state.*
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityListViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCityWeather(
    private val weatherCityNetworkDataSource: WeatherCityNetworkDataSource
) {

    fun getCityWeather(
        cityName: String,
        stateEvent: StateEvent
    ): Flow<DataState<CityListViewState>?> = flow {
        val networkResult = safeApiCall(IO) {
            weatherCityNetworkDataSource.getCityWeather(cityName)
        }

        val response = object : ApiResponseHandler<CityListViewState, CityWeather>(
            response = networkResult,
            stateEvent = null
        ) {
            override suspend fun handleSuccess(resultObj: CityWeather): DataState<CityListViewState>? {

                return if(resultObj.id.isNotEmpty()) {
                    val viewState =
                        CityListViewState(
                            cityWeather = resultObj
                        )
                    DataState.data(
                        response = Response(
                            message = "",
                            uiComponentType = UIComponentType.None(),
                            messageType = MessageType.Success()
                        ),
                        data = viewState,
                        stateEvent = stateEvent
                    )
                } else{
                    val viewState =
                        CityListViewState(
                            cityWeather = null
                        )
                    DataState.data(
                        response = Response(
                            message = "Some error occurred",
                            uiComponentType = UIComponentType.Toast(),
                            messageType = MessageType.Error()
                        ),
                        data = viewState,
                        stateEvent = stateEvent
                    )
                }
            }
        }.getResult()
        emit(response)
    }

}