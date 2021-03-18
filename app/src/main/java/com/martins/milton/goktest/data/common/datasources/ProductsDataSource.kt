package com.martins.milton.goktest.data.common.datasources

import com.martins.milton.goktest.data.remote.models.ProductsResponse

interface ProductsDataSource {
    suspend fun getProducts(): ProductsResponse
}