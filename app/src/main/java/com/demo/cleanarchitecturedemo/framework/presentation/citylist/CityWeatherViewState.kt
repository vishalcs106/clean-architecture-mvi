package com.demo.cleanarchitecturedemo.framework.presentation.citylist

import android.os.Parcelable
import com.demo.cleanarchitecturedemo.business.domain.model.CityWeather
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityWeatherViewState(
    var cityWeather: CityWeather? = null
): Parcelable{

}