package com.martins.milton.goktest.data.common.repositories

import com.martins.milton.goktest.data.common.datasources.ProductsDataSource
import com.martins.milton.goktest.data.remote.models.ProductsResponse
import javax.inject.Inject

interface ProductsRepository {
    suspend fun getProducts(): ProductsResponse
}

class ProductsRepositoryImpl @Inject constructor(
    private val dataSource: ProductsDataSource
) : ProductsRepository {
    override suspend fun getProducts(): ProductsResponse = dataSource.getProducts()
}