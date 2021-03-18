package com.martins.milton.goktest.ui.products

import androidx.lifecycle.Observer
import com.martins.milton.goktest.base.BaseTest
import com.martins.milton.goktest.data.ProductsMock
import com.martins.milton.goktest.data.common.repositories.ProductsRepository
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class ProductsViewModelTest : BaseTest() {

    @Mock
    private lateinit var repository: ProductsRepository

    @Mock
    private lateinit var stateObserver: Observer<ProductsState>

    private lateinit var viewModel: ProductsViewModel
    private val dataMock = ProductsMock()

    @Before
    fun setup() {
        viewModel = ProductsViewModel(
            repository = repository
        )

        viewModel.stateProducts.observeForever(stateObserver)
    }

    @Test
    fun `WHEN get products THEN must show products`() = runBlocking {
        //Arrange
        whenever(repository.getProducts()).thenReturn(dataMock.productsResponse)

        //Act
        viewModel.handleProductsIntent(ProductsIntent.LoadProducts)

        //Assert
        verify(
            mock = stateObserver,
            mode = atLeastOnce()
        ).onChanged(ProductsState.Loading(true))
        verify(
            mock = stateObserver,
            mode = atLeastOnce()
        ).onChanged(ProductsState.Loading(false))
        verify(
            mock = stateObserver,
            mode = atLeastOnce()
        ).onChanged(ProductsState.ShowProducts(dataMock.productsResponse))
    }

    @Test
    fun `GIVEN product name, description and image WHEN call intent go to product details THEN must open product details`() {
        //Act
        viewModel.handleProductsIntent(
            ProductsIntent.GoToProductDetails(
                name = dataMock.product.name,
                description = dataMock.product.description,
                imageUrl = dataMock.product.imageURL
            )
        )

        //Assert
        verify(
            mock = stateObserver,
            mode = atLeastOnce()
        ).onChanged(ProductsState.OpenProductDetails(dataMock.productDetail))
    }

}