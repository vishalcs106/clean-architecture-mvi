package com.demo.cleanarchitecturedemo.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherHourlyResponse {

    @SerializedName("list")
    @Expose
    var list: List<WeatherHourlyNetworkEntity>? = null

}