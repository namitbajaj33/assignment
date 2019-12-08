package com.assignment.dagger

import com.assignment.ui.celebritycars.CelebrityCarsViewModel
import com.assignment.ui.celebritycars.CelebrityCarsRepoImpl
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RetrofitModule::class,
        RepositoryModule::class
    ]
)

@Singleton
interface AppComponent {

    fun inject(celebrityCarsRepoImpl: CelebrityCarsRepoImpl)

    fun inject(celebrityCarsViewModel: CelebrityCarsViewModel)

}
