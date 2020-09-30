package com.demo.cleanarchitecturedemo.business.interators.city

import com.demo.cleanarchitecturedemo.business.data.cache.CacheResponseHandler
import com.demo.cleanarchitecturedemo.business.data.cache.abstratction.CityCacheDataSource
import com.demo.cleanarchitecturedemo.business.data.util.safeCacheCall
import com.demo.cleanarchitecturedemo.business.domain.model.City
import com.demo.cleanarchitecturedemo.business.domain.state.*
import com.demo.cleanarchitecturedemo.framework.presentation.citylist.CityListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoveCity(
    private val cityCacheDataSource: CityCacheDataSource
){
    fun removeCity(city: City, stateEvent: StateEvent): Flow<DataState<CityListViewState>?> = flow {
        val cacheResult = safeCacheCall(Dispatchers.IO) {
            cityCacheDataSource.removeCity(city.id)
        }

        val cacheResponse = object : CacheResponseHandler<CityListViewState, Unit>(
            response = cacheResult,
            stateEvent = stateEvent
        ) {
            override suspend fun handleSuccess(resultObj: Unit): DataState<CityListViewState>? {
                val viewState =
                    CityListViewState()
                return DataState.data(
                    response = Response(
                        message = "Successfully removed city.",
                        uiComponentType = UIComponentType.Toast(),
                        messageType = MessageType.Success()
                    ),
                    data = viewState,
                    stateEvent = stateEvent
                )

            }
        }.getResult()
        emit(cacheResponse)

    }
}