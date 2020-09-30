package com.demo.cleanarchitecturedemo.framework.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.cleanarchitecturedemo.business.interators.CityListInteractors
import com.demo.cleanarchitecturedemo.business.interators.WeatherDetailsInteractor
import com.demo.cleanarchitecturedemo.framework.presentation.MainViewModel
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityListViewModel
import com.demo.cleanarchitecturedemo.framework.presentation.hourlyweather.HourlyWeatherViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@Singleton
class ViewModelFactory
@Inject
constructor(
private val cityListInteractors: CityListInteractors,
private val weatherDetailsInteractor: WeatherDetailsInteractor
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return when(modelClass){
            CityListViewModel::class.java ->{
                CityListViewModel(
                    cityListInteractors
                ) as T
            }

           HourlyWeatherViewModel::class.java -> {
               HourlyWeatherViewModel(
                   weatherDetailsInteractor
               ) as T
           }

           else -> {
               throw IllegalArgumentException("unknown model class $modelClass")
           }
       }
    }

}