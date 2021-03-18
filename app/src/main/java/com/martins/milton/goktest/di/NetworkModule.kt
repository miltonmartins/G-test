package com.martins.milton.goktest.di

import com.martins.milton.goktest.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun retrofit(
        @Named(BASE_URL) baseUrl: String,
        httpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit.Builder {
        return Retrofit
            .Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
    }

    @Provides
    @Singleton
    fun converterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun httpClient(interceptors: @JvmSuppressWildcards(true) Set<Interceptor>): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        interceptors.forEach { interceptor ->
            httpClient.addInterceptor(interceptor)
        }

        return httpClient.build()
    }

    @Provides
    @ElementsIntoSet
    fun interceptors(): Set<Interceptor> = setOf()

    @Provides
    @IntoSet
    fun httpLogInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            this.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Named(BASE_URL)
    fun baseUrl(): String = BuildConfig.ENDPOINT

    companion object {
        const val BASE_URL = "BASE_URL"
    }
}
