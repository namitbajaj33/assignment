package com.assignment.ui.celebritycars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.assignment.R


class MainActivity : AppCompatActivity() {

    private lateinit var celebrityCarViewModel: CelebrityCarsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celebrityCarViewModel = ViewModelProviders.of(this).get(CelebrityCarsViewModel::class.java)
        celebrityCarViewModel.init()
        observeCelebrityCarList()
    }

    private fun observeCelebrityCarList() {
        celebrityCarViewModel.getResponse().observe(this, Observer {

        })
    }

}
