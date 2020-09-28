package com.demo.cleanarchitecturedemo.business.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityWeather(
    val id: String,
    val temperature: Double,
    val weather: String
): Parcelable {
}