package com.demo.cleanarchitecturedemo.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather(
    @SerializedName("main")
    @Expose
    var main: String = "",

    @SerializedName("description")
    @Expose
    var description: String = ""
) {

}