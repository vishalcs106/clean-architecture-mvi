package com.demo.cleanarchitecturedemo.framework.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.cleanarchitecturedemo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}