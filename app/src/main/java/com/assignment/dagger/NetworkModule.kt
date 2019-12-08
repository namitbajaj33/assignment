package com.assignment.dagger

import android.content.SharedPreferences
import com.assignment.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun providesInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val request = chain.request().newBuilder()
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Connection", "close")
                .header("Pragma", "no-cache")
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    fun providesOkHttpClient(queryInterceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(1, TimeUnit.MINUTES)
        builder.writeTimeout(1, TimeUnit.MINUTES)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }
        builder.addInterceptor(queryInterceptor)
        return builder.build()
    }
}