package com.martins.milton.goktest.di

import android.content.Context
import com.martins.milton.goktest.GoKTestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DataModule::class,
        NetworkModule::class,
        ApplicationModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<GoKTestApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}