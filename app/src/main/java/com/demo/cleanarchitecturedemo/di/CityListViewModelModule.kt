package com.demo.cleanarchitecturedemo.di

import androidx.lifecycle.ViewModelProvider
import com.demo.cleanarchitecturedemo.business.interators.CityListInteractors
import com.demo.cleanarchitecturedemo.business.interators.WeatherDetailsInteractor
import com.demo.cleanarchitecturedemo.framework.presentation.common.ViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@Module
object CityListViewModelModule {
    @Singleton
    @JvmStatic
    @Provides
    fun provideNoteViewModelFactory(cityListInteractors: CityListInteractors,
    weatherDetailsInteractor: WeatherDetailsInteractor): ViewModelProvider.Factory{
        return ViewModelFactory(cityListInteractors = cityListInteractors,
        weatherDetailsInteractor = weatherDetailsInteractor)
    }

}