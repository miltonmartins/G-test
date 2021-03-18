package com.martins.milton.goktest.di

import com.martins.milton.goktest.data.common.datasources.ProductsDataSource
import com.martins.milton.goktest.data.common.repositories.ProductsRepository
import com.martins.milton.goktest.data.common.repositories.ProductsRepositoryImpl
import com.martins.milton.goktest.data.remote.datasources.ProductsDataSourceRemote
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun providesProductsRepository(repository: ProductsRepositoryImpl): ProductsRepository

    @Binds
    fun providesProductsDataSource(dataSource: ProductsDataSourceRemote): ProductsDataSource
}