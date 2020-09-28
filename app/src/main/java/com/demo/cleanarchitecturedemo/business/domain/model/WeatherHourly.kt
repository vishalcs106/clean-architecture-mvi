package com.demo.cleanarchitecturedemo.business.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherHourly(val temp: Double,
                         val tempMin: Double,
                         val tempMax: Double,
                         val weather: String,
                         val weatherDesc: String,
                         val dateTime: String,
                         val timestamp: Long): Parcelable {}