package com.martins.milton.goktest.data.remote.datasources

import com.martins.milton.goktest.data.common.datasources.ProductsDataSource
import com.martins.milton.goktest.data.remote.models.ProductsResponse
import com.martins.milton.goktest.data.remote.services.ProductsService
import retrofit2.Retrofit
import javax.inject.Inject

class ProductsDataSourceRemote @Inject constructor(
    retrofit: Retrofit.Builder
) : ProductsDataSource {

    private val service = retrofit.build().create(ProductsService::class.java)

    override suspend fun getProducts(): ProductsResponse = service.getProducts()
}