package com.mokelab.demo.viewmodel

import com.mokelab.demo.core.data.DemoMokeraRepository
import com.mokelab.demo.core.data.MokeraRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {
    @Binds
    @Singleton
    abstract fun bindMokeraRepository(repository: DemoMokeraRepository): MokeraRepository
}