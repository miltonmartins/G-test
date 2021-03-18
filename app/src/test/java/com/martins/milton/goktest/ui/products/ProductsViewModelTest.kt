package com.martins.milton.goktest.ui.products

import androidx.lifecycle.Observer
import com.martins.milton.goktest.base.BaseTest
import com.martins.milton.goktest.data.ProductsMock
import com.martins.milton.goktest.data.common.repositories.ProductsRepository
import com.martins.milton.goktest.data.common.repositories.ProfileRepository
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class ProductsViewModelTest : BaseTest() {

    @Mock
    private lateinit var productsRepository: ProductsRepository

    @Mock
    private lateinit var profileRepository: ProfileRepository

    @Mock
    private lateinit var stateObserver: Observer<ProductsState>

    private lateinit var viewModel: ProductsViewModel
    private val dataMock = ProductsMock()

    @Before
    fun setup() {
        viewModel = ProductsViewModel(
            productsRepository = productsRepository,
            profileRepository = profileRepository
        )

        viewModel.stateProducts.observeForever(stateObserver)
    }

    @Test
    fun `WHEN get products and account details THEN must show products and account name`() = runBlocking {
        //Arrange
        whenever(productsRepository.getProducts()).thenReturn(dataMock.productsResponse)
        whenever(profileRepository.getAccount()).thenReturn(dataMock.account)

        //Act
        viewModel.handleProductsIntent(ProductsIntent.LoadProductsAndAccountDetails)

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
        ).onChanged(
            ProductsState.ShowProductsAndAccount(
                products = dataMock.productsResponse,
                account = dataMock.account
            )
        )
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