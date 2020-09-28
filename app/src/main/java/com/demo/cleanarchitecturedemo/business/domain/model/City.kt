package com.demo.cleanarchitecturedemo.business.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(val id: String,
                val name: String,
                val currentCity: Boolean = false): Parcelable {}