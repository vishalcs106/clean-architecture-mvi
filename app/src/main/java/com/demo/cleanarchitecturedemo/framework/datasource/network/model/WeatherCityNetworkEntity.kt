package com.demo.cleanarchitecturedemo.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherCityNetworkEntity(

    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("dt")
    @Expose
    var dt: Long,

    @SerializedName("main")
    @Expose
    var main: Main,

    @SerializedName("weather")
    @Expose
    var weather: Weather
)