package com.demo.cleanarchitecturedemo.framework.presentation.hourlyweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.cleanarchitecturedemo.R
import com.demo.cleanarchitecturedemo.business.domain.model.WeatherHourly
import com.demo.cleanarchitecturedemo.business.domain.util.DateUtil
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityListAdapter
import kotlinx.android.synthetic.main.hourlyweather_list_item.view.*

class HourlyWeatherAdapter(val dateUtil: DateUtil): RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder>() {

    private var weatherHourly: List<WeatherHourly> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return HourlyWeatherAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.hourlyweather_list_item,
                parent,
                false
            ), dateUtil)
    }

    override fun getItemCount(): Int {
        return weatherHourly.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weatherHourly[position])
    }

    fun updateHourlyWeather(hourlyWeather: List<WeatherHourly>) {
        weatherHourly = hourlyWeather
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View, private val dateUtil: DateUtil) : RecyclerView.ViewHolder(itemView){
        fun bind(weatherHourly: WeatherHourly) {
            itemView.time_tv.text = dateUtil.getTimeFromDate(weatherHourly.dateTime)
            itemView.temp_tv.text = "Current Temp: "+weatherHourly.temp+" F"
            itemView.tempmin_tv.text = "Min Temp: "+weatherHourly.tempMin+" F"
            itemView.tempmax_tv.text = "Max Temp: "+weatherHourly.tempMax+" F"
            itemView.weather_tv.text = "Weather: "+weatherHourly.weather
        }

    }

}