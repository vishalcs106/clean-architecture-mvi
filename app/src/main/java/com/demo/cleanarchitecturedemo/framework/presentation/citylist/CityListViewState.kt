package com.demo.cleanarchitecturedemo.framework.presentation.citylist

import android.os.Parcelable
import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.business.domain.model.CityWeather
import com.demo.cleanarchitecturedemo.business.domain.state.ViewState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityListViewState(
var cityList: ArrayList<City>? = null,
var cityWeather: CityWeather? = null,
var weatherUpdated: Boolean = false
): Parcelable, ViewState {
}