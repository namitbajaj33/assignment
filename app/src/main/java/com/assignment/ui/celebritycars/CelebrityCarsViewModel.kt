package com.assignment.ui.celebritycars

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.AssignmentApp
import javax.inject.Inject

class CelebrityCarsViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var getCelebrityCarsRepoImpl: CelebrityCarsRepoImpl

    private var response = MutableLiveData<Boolean>()

    fun init() {
        AssignmentApp.mAppComponent.inject(this)
        getCelebrityCarsRepoImpl.getCelebrityCars()
        response = getCelebrityCarsRepoImpl.response
    }

    fun getResponse(): LiveData<Boolean> {
        return response
    }

    override fun onCleared() {
        super.onCleared()
        getCelebrityCarsRepoImpl.onCancel()
    }
}