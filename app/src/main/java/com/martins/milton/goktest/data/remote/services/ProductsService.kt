package com.martins.milton.goktest.data.remote.services

import com.martins.milton.goktest.data.remote.models.ProductsResponse
import retrofit2.http.GET

interface ProductsService {
    @GET("products")
    suspend fun getProducts(): ProductsResponse
}