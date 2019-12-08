package com.assignment.dagger

import com.assignment.retrofit.AssignmentService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun providesRetrofitEndPoints(
        okHttpClient: OkHttpClient,
        rxAdapter: RxJava2CallAdapterFactory,
        gson: Gson
    ): AssignmentService {

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.myjson.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxAdapter)
            .build()
            .create(AssignmentService::class.java)
    }
}