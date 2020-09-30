package com.demo.cleanarchitecturedemo.business.interators.city

import com.demo.cleanarchitecturedemo.business.data.cache.CacheResponseHandler
import com.demo.cleanarchitecturedemo.business.data.cache.abstratction.CityCacheDataSource
import com.demo.cleanarchitecturedemo.business.data.util.safeCacheCall
import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.business.domain.state.*
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityListViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllCities(
    private val cityCacheDataSource: CityCacheDataSource
) {

    fun getAllCities(stateEvent: StateEvent): Flow<DataState<CityListViewState>?> = flow {
        val cacheResult = safeCacheCall(IO) {
            cityCacheDataSource.getAllCities()
        }

        val response = object : CacheResponseHandler<CityListViewState, List<City>>(
            response = cacheResult,
            stateEvent = stateEvent
        ) {

            override suspend fun handleSuccess(resultObj: List<City>): DataState<CityListViewState>? {
                var message: String? =
                    "Fetched saved cities"
                var uiComponentType: UIComponentType? = UIComponentType.None()
                if(resultObj.size == 0){
                    message =
                        "No cities saved"
                    uiComponentType = UIComponentType.Toast()
                }
                return DataState.data(
                    response = Response(
                        message = message,
                        uiComponentType = uiComponentType as UIComponentType,
                        messageType = MessageType.Success()
                    ),
                    data = CityListViewState(
                        cityList = ArrayList(resultObj)
                    ),
                    stateEvent = stateEvent
                )
            }
        }.getResult()
        emit(response)
    }

}