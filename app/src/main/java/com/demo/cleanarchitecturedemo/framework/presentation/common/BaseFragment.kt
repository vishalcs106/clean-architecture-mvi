package com.demo.cleanarchitecturedemo.framework.presentation.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.demo.cleanarchitecturedemo.di.AppComponent
import com.demo.cleanarchitecturedemo.framework.presentation.ThisApplication

abstract class BaseFragment
    constructor(
        private @LayoutRes val layoutRes: Int
    ): Fragment(){

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    fun getAppComponent(): AppComponent {
        return activity?.run {
            (application as ThisApplication).appComponent
        }?: throw Exception("AppComponent is null.")
    }

    abstract fun inject()
}