package com.demo.cleanarchitecturedemo.framework.presentation

import android.app.Application
import com.demo.cleanarchitecturedemo.di.AppComponent
import com.demo.cleanarchitecturedemo.di.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
open class ThisApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    open fun initAppComponent(){
        appComponent = DaggerAppComponent
            .factory()
            .create(this)
    }

}