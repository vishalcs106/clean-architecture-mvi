package com.demo.cleanarchitecturedemo.framework.presentation.citylist.state

import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.business.domain.state.StateEvent

sealed class HourlyWeatherStateEvent: StateEvent {

    class GetHourlyWeather(val city: City): HourlyWeatherStateEvent(){

        override fun errorInfo(): String {
            return "Error fetching weather forecast"
        }

        override fun eventName(): String {
            return "GetHourlyWeather"
        }

        override fun shouldDisplayProgressBar(): Boolean = true

    }

}