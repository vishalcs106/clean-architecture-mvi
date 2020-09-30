package com.demo.cleanarchitecturedemo.framework.presentation.hourlyweather

import com.demo.cleanarchitecturedemo.business.domain.model.WeatherHourly

data class HourlyWeatherViewstate(
    var hourlyWeather: List<WeatherHourly>? = null
) {
}