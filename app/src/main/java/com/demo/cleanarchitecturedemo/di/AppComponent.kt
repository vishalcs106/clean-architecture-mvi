package com.demo.cleanarchitecturedemo.di

import com.demo.cleanarchitecturedemo.framework.presentation.MainActivity
import com.demo.cleanarchitecturedemo.framework.presentation.ThisApplication
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityListFragment
import com.demo.cleanarchitecturedemo.framework.presentation.hourlyweather.WeatherDetailFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@Singleton
@Component(
    modules = [AppModule::class, CityListFragmentFactoryModule::class, CityListViewModelModule::class])
interface AppComponent {
    fun inject(cityListFragment: CityListFragment)
    fun inject(activity: MainActivity)
    fun inject(weatherDetailFragment: WeatherDetailFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: ThisApplication): AppComponent
    }

}