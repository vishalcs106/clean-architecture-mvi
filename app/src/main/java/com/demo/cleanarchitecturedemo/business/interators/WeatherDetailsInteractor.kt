package com.demo.cleanarchitecturedemo.business.interators

import com.demo.cleanarchitecturedemo.business.interators.weather.GetHourlyWeather

class WeatherDetailsInteractor(
    val getHourlyWeather: GetHourlyWeather
)