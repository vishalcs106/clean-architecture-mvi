package com.demo.cleanarchitecturedemo.framework.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.cleanarchitecturedemo.R
import com.demo.cleanarchitecturedemo.framework.presentation.common.AppFragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: AppFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        setFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

}