package com.martins.milton.goktest.data.remote.models

data class ProductsResponse(
    val cash: CashResponse,
    val products: List<ItemProductResponse>,
    val spotlight: List<SpotlightResponse>
)


