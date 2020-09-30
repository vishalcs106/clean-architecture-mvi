package com.demo.cleanarchitecturedemo.business.interators.weather

import com.demo.cleanarchitecturedemo.business.data.network.ApiResponseHandler
import com.demo.cleanarchitecturedemo.business.data.network.abstraction.WeatherHourlyNetworkDataSource
import com.demo.cleanarchitecturedemo.business.data.util.safeApiCall
import com.demo.cleanarchitecturedemo.business.domain.model.WeatherHourly
import com.demo.cleanarchitecturedemo.business.domain.state.*
import com.demo.cleanarchitecturedemo.framework.presentation.hourlyweather.HourlyWeatherViewstate
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHourlyWeather(
    private val weatherHourlyNetworkDataSource: WeatherHourlyNetworkDataSource
) {
    fun getWeatherHourly(
        cityName: String,
        stateEvent: StateEvent
    ): Flow<DataState<HourlyWeatherViewstate>?> = flow {
        val networkResult = safeApiCall(IO) {
            weatherHourlyNetworkDataSource.getWeatherHourly(cityName)
        }

        val response = object : ApiResponseHandler<HourlyWeatherViewstate, List<WeatherHourly>>(
            response = networkResult,
            stateEvent = null
        ) {
            override suspend fun handleSuccess(resultObj: List<WeatherHourly>): DataState<HourlyWeatherViewstate>? {

                return if (resultObj.isNotEmpty()) {
                    val viewState =
                        HourlyWeatherViewstate(
                            hourlyWeather = resultObj
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
                } else {
                    val viewState =
                        HourlyWeatherViewstate(
                            hourlyWeather = null
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