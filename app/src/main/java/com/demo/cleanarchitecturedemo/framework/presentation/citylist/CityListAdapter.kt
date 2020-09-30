package com.demo.cleanarchitecturedemo.framework.presentation.citylist

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.cleanarchitecturedemo.R
import com.demo.cleanarchitecturedemo.business.domain.model.City
import kotlinx.android.synthetic.main.city_list_item.view.*

class CityListAdapter
    constructor(
        val interactions: Interactions
    ): RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    private var cityList: MutableList<City> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.city_list_item,
            parent,
            false
        ), interactions)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    fun updateCityList(list: java.util.ArrayList<City>) {
        if(list.size > cityList.size) {
            var newArrList: ArrayList<City> = ArrayList()
            newArrList.addAll(list)
            cityList.clear()
            cityList.addAll(newArrList)
        }
    }

    fun updateCity(newCity: City, addCity: Boolean) {
        var found = false
        for((index, city) in cityList.withIndex()){
            if(city.id == newCity.id){
                if(addCity) cityList.set(index, newCity)
                else cityList.removeAt(index)
                found = true
                break
            }
        }
        if(!found) cityList.add(newCity)
        notifyDataSetChanged()
    }

    class ViewHolder(
        itemView: View,
        private val interactions: Interactions
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: City) {
            itemView.city_name.text = city.name
            city.cityWeather?.let { itemView.city_temp.text = "Temp: "+it.temperature.toString() +" F"}
            itemView.setOnClickListener {
               interactions.onCityTapped(city)
            }
            itemView.remove_iv.setOnClickListener {
                interactions.onRemoveCityTapped(city)
            }
        }

    }

    interface Interactions{
        fun onCityTapped(city: City)
        fun onRemoveCityTapped(city: City)
    }

}