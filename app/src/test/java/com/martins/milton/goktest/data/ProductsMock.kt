package com.martins.milton.goktest.data

import com.martins.milton.goktest.data.local.models.ProductDetail
import com.martins.milton.goktest.data.remote.models.*

data class ProductsMock(
    val productsResponse: ProductsResponse = ProductsResponse(
        cash = CashResponse(
            title = "digio Cash",
            bannerURL = "https://www.google.com/img=123",
            description = "Dinheiro na conta sem complicação. Transfira parte do limite do seu cartão para sua conta"
        ),
        products = listOf(
            ItemProductResponse(
                name = "XBOX",
                description = "Com o e-Gift Card Xbox você adquire créditos para comprar games, música, filmes, programas de TV e muito mais!",
                imageURL = "https://www.google.com/img=124"
            ),
            ItemProductResponse(
                name = "Google Play",
                description = "O e-gift Google Play dá acesso a um mundo de entretenimento. É muito fácil encontrar algo que você goste, seja você um fã de música ou de filmes, livros, revistas, apps ou jogos.",
                imageURL = "https://www.google.com/img=125"
            )
        ),
        spotlight = listOf(
            SpotlightResponse(
                name = "Recarga",
                description = "Agora ficou mais fácil colocar créditos no seu celular! A digio Store traz a facilidade de fazer recargas...",
                bannerURL = "https://www.google.com/img=126"
            ),
            SpotlightResponse(
                name = "Uber",
                description = "Dê um vale presente Uber para amigos e familiares, ou use os vales para adicionar créditos à sua conta.",
                bannerURL = "https://www.google.com/img=127"
            )
        )
    ),
    val product: ItemProductResponse = productsResponse.products.first(),
    val productDetail: ProductDetail = ProductDetail(
        name = product.name,
        description = product.description,
        image = product.imageURL
    ),
    val account: AccountResponse = AccountResponse(
        name = "Maria",
        photoUrl = "https://br.web.img3.acsta.net/newsv7/20/06/04/15/14/4885819.jpg"
    )
)