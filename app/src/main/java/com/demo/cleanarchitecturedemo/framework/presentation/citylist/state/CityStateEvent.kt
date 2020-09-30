package com.demo.cleanarchitecturedemo.framework.presentation.citylist.state

import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.business.domain.state.StateEvent

sealed class CityStateEvent: StateEvent {

    class AddCityEvent(val city: City, val cityList: List<City>?): CityStateEvent(){
        override fun errorInfo(): String {
            return "City was not added"
        }

        override fun eventName(): String {
            return "AddCityEvent"
        }

        override fun shouldDisplayProgressBar(): Boolean = true

    }


    class RemoveCityEvent(val city: City): CityStateEvent(){
        override fun errorInfo(): String {
            return "City was not removed"
        }

        override fun eventName(): String {
            return "RemoveCityEvent"
        }

        override fun shouldDisplayProgressBar(): Boolean = true

    }

    class GetCitiesEvent(): CityStateEvent(){
        override fun errorInfo(): String {
            return "Could not fetch cities"
        }

        override fun eventName(): String {
            return "GetCitiesEvent"
        }

        override fun shouldDisplayProgressBar(): Boolean = true

    }


    class GetCityWeatherEvent(val cityName: String): CityStateEvent(){
        override fun errorInfo(): String {
            return "Could not fetch city weather"
        }

        override fun eventName(): String {
            return "GetCityWeatherEvent"
        }

        override fun shouldDisplayProgressBar(): Boolean = true

    }



}