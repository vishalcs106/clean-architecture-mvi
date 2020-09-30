package com.demo.cleanarchitecturedemo.framework.presentation.citylist

import androidx.lifecycle.ViewModel
import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.business.domain.model.CityWeather
import com.demo.cleanarchitecturedemo.business.domain.state.DataState
import com.demo.cleanarchitecturedemo.business.domain.state.StateEvent
import com.demo.cleanarchitecturedemo.business.interators.CityListInteractors
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.state.CityStateEvent
import com.demo.cleanarchitecturedemo.framework.presentation.common.BaseViewModel
import kotlinx.coroutines.flow.Flow

class CityListViewModel
    constructor(
        private val cityListInteractors: CityListInteractors
    ): BaseViewModel<CityListViewState>(){

    override fun handleNewData(data: CityListViewState) {
        data.let {viewState ->
            viewState.cityList?.let { cityList ->
                setCityList(cityList)
            }
            viewState.cityWeather?.let {
                setCityWeather(it)
            }

        }
    }

    private fun setCityList(cityList: ArrayList<City>) {
        val update = getCurrentViewStateOrNew()
        update.cityList = cityList
        setViewState(update)
    }

    private fun setCityWeather(cityWeather: CityWeather) {
        val update = getCurrentViewStateOrNew()
        update.cityWeather = cityWeather
        update.weatherUpdated = true
        setViewState(update)
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        val job: Flow<DataState<CityListViewState>?> = when(stateEvent){
            is CityStateEvent.GetCitiesEvent -> {
                cityListInteractors.getAllCities.getAllCities(stateEvent)
            }
            is CityStateEvent.GetCityWeatherEvent ->{
                cityListInteractors.getCityWeather.getCityWeather(stateEvent.cityName, stateEvent)
            }

            is CityStateEvent.AddCityEvent -> {
                cityListInteractors.addCity.addCity(stateEvent.city, stateEvent.cityList, stateEvent)
            }

            is CityStateEvent.RemoveCityEvent -> {
                cityListInteractors.removeCity.removeCity(stateEvent.city, stateEvent)
            }

            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }
        launchJob(stateEvent, job)
    }

    override fun initNewViewState(): CityListViewState {
       return CityListViewState()
    }

}