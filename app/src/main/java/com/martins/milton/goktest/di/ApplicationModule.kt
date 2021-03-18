package com.martins.milton.goktest.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class ApplicationModule {

    @Provides
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO
}