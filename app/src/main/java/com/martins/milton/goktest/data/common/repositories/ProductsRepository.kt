package com.martins.milton.goktest.data.common.repositories

import com.martins.milton.goktest.data.common.datasources.ProductsDataSource
import com.martins.milton.goktest.data.remote.models.CashResponse
import com.martins.milton.goktest.data.remote.models.ItemProductResponse
import com.martins.milton.goktest.data.remote.models.ProductsResponse
import com.martins.milton.goktest.data.remote.models.SpotlightResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

interface ProductsRepository {
    suspend fun getProducts(): ProductsResponse
}

class ProductsRepositoryImpl @Inject constructor(
    private val dataSource: ProductsDataSource
) : ProductsRepository {
    override suspend fun getProducts(): ProductsResponse = dataSource.getProducts()
}

class ProductsRepositoryFake @Inject constructor() : ProductsRepository {
    override suspend fun getProducts(): ProductsResponse {
        delay(2000L)
        val imageUrl =
            "https://venngage-wordpress.s3.amazonaws.com/uploads/2018/05/youtube-banner-template.png"
        return ProductsResponse(
            cash = CashResponse(
                bannerURL = imageUrl,
                description = "Description",
                title = "digio title"
            ),
            products = listOf(
                ItemProductResponse(
                    description = "description",
                    name = "name",
                    imageURL = imageUrl
                ),
                ItemProductResponse(
                    description = "description",
                    name = "name2",
                    imageURL = imageUrl
                ),
                ItemProductResponse(
                    description = "description",
                    name = "name3",
                    imageURL = imageUrl
                )
            ),
            spotlight = listOf(
                SpotlightResponse(
                    bannerURL = imageUrl,
                    description = "description",
                    name = "name"
                ),
                SpotlightResponse(
                    bannerURL = imageUrl,
                    description = "description2",
                    name = "name2"
                ),
                SpotlightResponse(
                    bannerURL = imageUrl,
                    description = "description3",
                    name = "name3"
                )
            )
        )
    }
}