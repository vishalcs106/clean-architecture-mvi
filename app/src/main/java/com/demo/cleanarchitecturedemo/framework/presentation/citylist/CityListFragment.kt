package com.demo.cleanarchitecturedemo.framework.presentation.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.cleanarchitecturedemo.R
import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.di.AppComponent
import com.demo.cleanarchitecturedemo.framework.presentation.ThisApplication
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.state.CityStateEvent
import com.demo.cleanarchitecturedemo.framework.presentation.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_city_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class CityListFragment
    constructor(
        private val viewModelFactory: ViewModelProvider.Factory
    ): BaseFragment(R.layout.fragment_city_list), CityListAdapter.Interactions {

    private var listAdapter: CityListAdapter? = null
    
    val viewModel: CityListViewModel by viewModels {
        viewModelFactory
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setupChannel()
    }
    override fun inject() {
        getAppComponent().inject(this)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribeObservers()
        getSavedCities()
    }

    private fun initView() {
        editTextCityName.setOnEditorActionListener { textView, actionId, keyEvent ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                viewModel.setStateEvent(CityStateEvent.GetCityWeatherEvent(
                    cityName = editTextCityName.text.toString()))
            }
            true
        }
    }

    private fun subscribeObservers(){
        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->

        if(viewState != null){
            viewState.cityList?.let {
                updateRecyclerView(it, viewState)
            }
            viewState.cityWeather?.let {
                val city = City(it.id, it.name)
                city.cityWeather = it
                addCity(city, viewState.cityList)
                updateRecyclerView(city, true)
            }
        }

        })
    }

    private fun updateRecyclerView(list: ArrayList<City>, viewState: CityListViewState) {
        initRecyclerView()
        listAdapter!!.updateCityList(list)
        recyclerview.postDelayed(Runnable {
            listAdapter!!.notifyDataSetChanged()
        }, 100)
        if(!viewState.weatherUpdated) {
            getCitiesWeather(list)
        }
    }

    private fun updateRecyclerView(city: City, addCity: Boolean) {
        initRecyclerView()
        listAdapter!!.updateCity(city, addCity)
    }

    private fun initRecyclerView() {
        if(listAdapter == null){
            listAdapter = CityListAdapter(this)
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
            recyclerview.adapter = listAdapter
        }
    }

    private fun getCitiesWeather(list: java.util.ArrayList<City>) {
        for(city in list){
            viewModel.setStateEvent(CityStateEvent.GetCityWeatherEvent(city.name))
        }
    }

    private fun addCity(city: City, cityList: List<City>?){
        viewModel.setStateEvent(CityStateEvent.AddCityEvent(city = city, cityList = cityList))
    }

    private fun getSavedCities(){
        viewModel.setStateEvent(
            CityStateEvent.GetCitiesEvent()
        )
    }

    override fun onCityTapped(city: City) {
        val bundle= Bundle()
        bundle.putParcelable("city", city)
        findNavController().navigate(R.id.action_cityListFragment_to_weatherDetailFragment, bundle)
    }

    override fun onRemoveCityTapped(city: City) {
        viewModel.setStateEvent(CityStateEvent.RemoveCityEvent(city = city))
        updateRecyclerView(city, false)
    }
}