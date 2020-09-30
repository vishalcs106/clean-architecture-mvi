package com.demo.cleanarchitecturedemo.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.demo.cleanarchitecturedemo.framework.presentation.common.AppFragmentFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@Module
object CityListFragmentFactoryModule {

    @JvmStatic
    @Singleton
    @Provides
    fun providesAppFragmentFactory(
        viewModelFactory: ViewModelProvider.Factory
    ): FragmentFactory {
        return AppFragmentFactory(
            viewModelFactory
        )
    }

}