package com.assignment.dagger

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

    //fun inject(xyzRepoImpl: XyzRepoImpl)


}
