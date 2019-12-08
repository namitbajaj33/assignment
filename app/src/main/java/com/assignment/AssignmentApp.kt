package com.assignment

import android.app.Application
import com.assignment.dagger.AppComponent
import com.assignment.dagger.AppModule
import com.assignment.dagger.DaggerAppComponent

class AssignmentApp : Application() {

    companion object {

        lateinit var mAppComponent: AppComponent

    }

    override fun onCreate() {
        super.onCreate()
        initDagger2()
    }

    private fun initDagger2() {
        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}