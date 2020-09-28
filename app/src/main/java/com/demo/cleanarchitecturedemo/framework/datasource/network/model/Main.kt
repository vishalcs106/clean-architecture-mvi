package com.demo.cleanarchitecturedemo.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main(
    @SerializedName("temp")
    @Expose
    var temp: Double = 0.0,

    @SerializedName("temp_min")
    @Expose
    var tempMin: Double = 0.0,

    @SerializedName("temp_max")
    @Expose
    var tempMax: Double = 0.0
) {


}