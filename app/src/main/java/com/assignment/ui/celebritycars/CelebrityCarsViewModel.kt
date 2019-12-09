package com.assignment.ui.celebritycars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.AssignmentApp
import com.assignment.model.CelebrityCarContainer
import javax.inject.Inject

class CelebrityCarsViewModel() : ViewModel() {

    @Inject
    lateinit var getCelebrityCarsRepoImpl: CelebrityCarsRepoImpl

    private var response = MutableLiveData<ArrayList<CelebrityCarContainer>>()

    init {
        AssignmentApp.mAppComponent.inject(this)
        response = getCelebrityCarsRepoImpl.response
    }

    fun getCelebrityCars() {
        getCelebrityCarsRepoImpl.getCelebrityCars()
    }

    fun getResponse(): LiveData<ArrayList<CelebrityCarContainer>> {
        return response
    }

    override fun onCleared() {
        super.onCleared()
        getCelebrityCarsRepoImpl.onCancel()
    }
}