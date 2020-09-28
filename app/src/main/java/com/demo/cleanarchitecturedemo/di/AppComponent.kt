package com.demo.cleanarchitecturedemo.di

import com.demo.cleanarchitecturedemo.framework.presentation.ThisApplication
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@Singleton
@Component(
    modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: ThisApplication): AppComponent
    }

}