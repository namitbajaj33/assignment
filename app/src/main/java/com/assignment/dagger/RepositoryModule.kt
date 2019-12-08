package com.assignment.dagger


import com.assignment.ui.celebritycars.CelebrityCarsRepoImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesCelebrityCarsRepo(): CelebrityCarsRepoImpl {
        return CelebrityCarsRepoImpl()
    }

}