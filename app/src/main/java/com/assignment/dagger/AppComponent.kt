package com.assignment.dagger

import com.assignment.repository.AssignmentRepoImpl
import com.assignment.ui.celebritycars.CelebrityCarsViewModel
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

    fun inject(assignmentRepoImpl: AssignmentRepoImpl)

    fun inject(celebrityCarsViewModel: CelebrityCarsViewModel)

}
