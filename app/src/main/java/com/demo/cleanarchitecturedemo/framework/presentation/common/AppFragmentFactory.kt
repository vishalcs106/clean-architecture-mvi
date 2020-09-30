package com.demo.cleanarchitecturedemo.framework.presentation.common

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityListFragment
import com.demo.cleanarchitecturedemo.framework.presentation.hourlyweather.WeatherDetailFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class AppFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) =
        when(className){
            CityListFragment::class.java.name -> {
                val fragment = CityListFragment(viewModelFactory)
                fragment
            }
            WeatherDetailFragment::class.java.name ->{
                val fragment = WeatherDetailFragment(viewModelFactory)
                fragment
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }

}