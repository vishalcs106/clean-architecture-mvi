package com.demo.cleanarchitecturedemo.business.interators

import com.demo.cleanarchitecturedemo.business.interators.city.AddCity
import com.demo.cleanarchitecturedemo.business.interators.city.GetAllCities
import com.demo.cleanarchitecturedemo.business.interators.city.GetCityWeather
import com.demo.cleanarchitecturedemo.business.interators.city.RemoveCity

class CityListInteractors(
    val addCity: AddCity,
    val removeCity: RemoveCity,
    val getCityWeather: GetCityWeather,
    val getAllCities: GetAllCities
)