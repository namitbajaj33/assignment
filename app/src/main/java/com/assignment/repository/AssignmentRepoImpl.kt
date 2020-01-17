package com.assignment.repository

import android.app.Application
import android.content.SharedPreferences
import com.assignment.AssignmentApp
import com.assignment.retrofit.AssignmentService
import com.google.gson.Gson
import javax.inject.Inject

abstract class AssignmentRepoImpl() {

    @Inject
    lateinit var assignmentService: AssignmentService

    @Inject
    lateinit var mGson: Gson

    @Inject
    lateinit var mSharedPreferences: SharedPreferences

    @Inject
    lateinit var mApp: Application

    abstract fun onCancel()

    init {
        AssignmentApp.mAppComponent.inject(this)
    }
}