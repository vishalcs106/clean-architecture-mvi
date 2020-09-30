package com.demo.cleanarchitecturedemo.framework.presentation.hourlyweather

import androidx.lifecycle.ViewModel
import com.demo.cleanarchitecturedemo.business.domain.state.DataState
import com.demo.cleanarchitecturedemo.business.domain.state.StateEvent
import com.demo.cleanarchitecturedemo.business.interators.CityListInteractors
import com.demo.cleanarchitecturedemo.business.interators.WeatherDetailsInteractor
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityListViewState
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.state.HourlyWeatherStateEvent
import com.demo.cleanarchitecturedemo.framework.presentation.common.BaseViewModel
import kotlinx.coroutines.flow.Flow

class HourlyWeatherViewModel
constructor(
    private val weatherDetailsInteractor: WeatherDetailsInteractor
): BaseViewModel<HourlyWeatherViewstate>() {

    override fun handleNewData(data: HourlyWeatherViewstate) {
        data.let {viewState ->
            viewState.hourlyWeather?.let {
                setHourlyWeather(viewState)
            }

        }
    }

    private fun setHourlyWeather(viewstate: HourlyWeatherViewstate) {
        val update = getCurrentViewStateOrNew()
        update.hourlyWeather = viewstate.hourlyWeather
        setViewState(update)
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        val job: Flow<DataState<HourlyWeatherViewstate>?> = when(stateEvent){
                is HourlyWeatherStateEvent.GetHourlyWeather -> {
                    weatherDetailsInteractor.getHourlyWeather.
                    getWeatherHourly(stateEvent.city.name, stateEvent)
                }
            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }
        launchJob(stateEvent, job)
    }

    override fun initNewViewState(): HourlyWeatherViewstate {
        return HourlyWeatherViewstate()
    }
}