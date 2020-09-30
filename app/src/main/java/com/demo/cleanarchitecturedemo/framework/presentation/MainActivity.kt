package com.demo.cleanarchitecturedemo.framework.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import com.demo.cleanarchitecturedemo.R
import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityListFragment
import com.demo.cleanarchitecturedemo.framework.presentation.common.AppFragmentFactory
import com.demo.cleanarchitecturedemo.framework.presentation.hourlyweather.WeatherDetailFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity(), CityListFragment.Interaction {

    @Inject
    lateinit var fragmentFactory: AppFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        setFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = fragmentFactory.instantiate(classLoader, CityListFragment::class.java.name)
        (fragment as CityListFragment).interaction = this
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commitNow()
    }

    private fun setFragmentFactory(){
        supportFragmentManager.fragmentFactory = fragmentFactory
    }

    private fun inject(){
        (application as ThisApplication).appComponent
            .inject(this)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onCityTapped(city: City) {
        val fragment = fragmentFactory.instantiate(classLoader, WeatherDetailFragment::class.java.name)
        val bundle = Bundle()
        bundle.putParcelable("city", city)
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}