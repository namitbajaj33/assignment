package com.assignment.ui.celebritycars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.R
import com.assignment.model.CelebrityCarContainer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var celebrityCarViewModel: CelebrityCarsViewModel

    private val celebriryCarList: ArrayList<CelebrityCarContainer> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        val mainActivityAdapter = MainActivityAdapter(celebriryCarList)
        recycler_view.adapter = mainActivityAdapter

        celebrityCarViewModel = ViewModelProviders.of(this).get(CelebrityCarsViewModel::class.java)
        observeCelebrityCarList()

        if (savedInstanceState == null) {
            getCelebrityCars()
        }
    }

    private fun observeCelebrityCarList() {
        celebrityCarViewModel.getResponse().observe(this, Observer {
            celebriryCarList.clear()
            celebriryCarList.addAll(it)
            recycler_view.adapter?.notifyDataSetChanged()
            shimmer_view_container.visibility = View.GONE
            shimmer_view_container.stopShimmerAnimation()
        })
    }

    private fun getCelebrityCars() {
        shimmer_view_container.visibility = View.VISIBLE
        shimmer_view_container.startShimmerAnimation()
        celebrityCarViewModel.getCelebrityCars()
    }
}
