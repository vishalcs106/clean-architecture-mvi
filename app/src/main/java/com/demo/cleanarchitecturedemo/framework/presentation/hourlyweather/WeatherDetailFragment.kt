package com.demo.cleanarchitecturedemo.framework.presentation.hourlyweather

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.cleanarchitecturedemo.R
import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.business.domain.model.WeatherHourly
import com.demo.cleanarchitecturedemo.business.domain.util.DateUtil
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.state.HourlyWeatherStateEvent
import com.demo.cleanarchitecturedemo.framework.presentation.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_city_list.recyclerview
import kotlinx.android.synthetic.main.fragment_weather_detail.*
import javax.inject.Inject


class WeatherDetailFragment  constructor(
    private val viewModelFactory: ViewModelProvider.Factory
): BaseFragment(R.layout.fragment_weather_detail) {

    private var listAdapter: HourlyWeatherAdapter? = null
    @Inject
    lateinit var dataUtil: DateUtil

    val viewModel: HourlyWeatherViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setupChannel()
    }

    override fun inject() {
        getAppComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readArgs()
        subscribeObservers()
    }

    private fun readArgs() {
        arguments?.let { args ->
            args.getParcelable<City>("city")?.let { city ->
                city_tv.text = city.name
                getHourlyWeather(city)
            }
        }
    }

    private fun getHourlyWeather(city: City) {
        viewModel.setStateEvent(HourlyWeatherStateEvent.GetHourlyWeather(city))
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState->
            viewState.hourlyWeather?.let {
                date_tv.text = dataUtil.getDateString(it[0].dateTime)
                updateRecyclerView(it)
            }
        })
    }

    private fun updateRecyclerView(hourlyWeather: List<WeatherHourly>) {
        initRecyclerView()
        listAdapter!!.updateHourlyWeather(hourlyWeather)
    }

    private fun initRecyclerView() {
        if(listAdapter == null){
            listAdapter = HourlyWeatherAdapter(dataUtil)
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
            recyclerview.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerview.adapter = listAdapter
        }
    }

}