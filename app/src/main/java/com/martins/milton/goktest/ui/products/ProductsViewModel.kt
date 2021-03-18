package com.martins.milton.goktest.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martins.milton.goktest.data.common.repositories.ProductsRepository
import com.martins.milton.goktest.data.local.models.ProductDetail
import com.martins.milton.goktest.data.remote.models.ProductsResponse
import com.martins.milton.goktest.utils.ext.customLaunch
import javax.inject.Inject

sealed class ProductsState {
    data class ShowProducts(val products: ProductsResponse) : ProductsState()
    data class Loading(val loading: Boolean) : ProductsState()
    data class Error(val message: String?) : ProductsState()
    data class OpenProductDetails(val product: ProductDetail) : ProductsState()
}

sealed class ProductsIntent {
    object LoadProducts : ProductsIntent()
    data class GoToProductDetails(
        val name: String,
        val description: String,
        val imageUrl: String
    ) : ProductsIntent()
}

class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {

    private val _stateProducts = MutableLiveData<ProductsState>()
    val stateProducts: LiveData<ProductsState>
        get() = _stateProducts

    fun handleProductsIntent(intent: ProductsIntent) {
        when (intent) {
            ProductsIntent.LoadProducts -> loadProducts()
            is ProductsIntent.GoToProductDetails -> goToProductDetails(
                name = intent.name,
                description = intent.description,
                imageUrl = intent.imageUrl
            )
        }
    }

    private fun loadProducts() {
        viewModelScope.customLaunch(
            blockToRun = { repository.getProducts() },
            onLoading = { _stateProducts.value = ProductsState.Loading(it) },
            onSuccess = { _stateProducts.value = ProductsState.ShowProducts(it) },
            onError = { _stateProducts.value = ProductsState.Error(it.message) }
        )
    }

    private fun goToProductDetails(
        name: String,
        description: String,
        imageUrl: String
    ) {
        _stateProducts.value = ProductsState.OpenProductDetails(
            ProductDetail(
                name = name,
                description = description,
                image = imageUrl
            )
        )
    }

}