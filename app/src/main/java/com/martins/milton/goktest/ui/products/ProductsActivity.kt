package com.martins.milton.goktest.ui.products

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.martins.milton.goktest.R
import com.martins.milton.goktest.data.local.models.ProductDetail
import com.martins.milton.goktest.data.remote.models.*
import com.martins.milton.goktest.databinding.ActivityProductsBinding
import com.martins.milton.goktest.databinding.ItemProductBinding
import com.martins.milton.goktest.databinding.ItemSpotlightBinding
import com.martins.milton.goktest.ui.common.SimpleRecyclerViewAdapter
import com.martins.milton.goktest.utils.ext.*
import com.martins.milton.goktest.utils.helper.ImageHelper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ProductsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ProductsViewModel> { viewModelFactory }
    private val imageHelper: ImageHelper by lazy { ImageHelper(baseContext) }

    private lateinit var binding: ActivityProductsBinding
    private lateinit var productsAdapter: SimpleRecyclerViewAdapter<ItemProductResponse, ItemProductBinding>
    private lateinit var spotlightsAdapter: SimpleRecyclerViewAdapter<SpotlightResponse, ItemSpotlightBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setView()
        setObservers()
        setListeners()

        viewModel.handleProductsIntent(ProductsIntent.LoadProductsAndAccountDetails)
    }

    private fun setView() = binding.run {
        spotlightRv.setup(
            adapter = spotlightsAdapter,
            layoutManager = LinearLayoutManager(
                spotlightRv.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )

        productsRv.setup(
            adapter = productsAdapter,
            layoutManager = LinearLayoutManager(
                productsRv.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
    }

    private fun setAdapter() {
        productsAdapter = SimpleRecyclerViewAdapter(
            clazz = ItemProductBinding::class.java
        ) { _, item, view ->
            imageHelper.loadImage(
                url = item.imageURL,
                view = view.itemProductIv
            )

            view.root.setOnClickListener {
                viewModel.handleProductsIntent(
                    ProductsIntent.GoToProductDetails(
                        name = item.name,
                        description = item.description,
                        imageUrl = item.imageURL
                    )
                )
            }
        }

        spotlightsAdapter = SimpleRecyclerViewAdapter(
            clazz = ItemSpotlightBinding::class.java
        ) { _, item, view ->
            imageHelper.loadImage(
                url = item.bannerURL,
                view = view.itemSpotlightIv
            )

            view.root.setOnClickListener {
                viewModel.handleProductsIntent(
                    ProductsIntent.GoToProductDetails(
                        name = item.name,
                        description = item.description,
                        imageUrl = item.bannerURL
                    )
                )
            }
        }
    }

    private fun setObservers() {
        viewModel.stateProducts.observe(this, { state ->
            when (state) {
                is ProductsState.Loading -> setLoading(state.loading)
                is ProductsState.Error -> setError(state.message)
                is ProductsState.OpenProductDetails -> openProductDetails(state.product)
                is ProductsState.ShowProductsAndAccount -> showProductsAndAccountName(
                    products = state.products,
                    account = state.account
                )
            }
        })
    }

    private fun setListeners() = binding.run {
        productsRl.setOnRefreshListener {
            viewModel.handleProductsIntent(ProductsIntent.LoadProductsAndAccountDetails)
        }
    }

    private fun setLoading(loading: Boolean) = binding.run {
        productsRl.isRefreshing = loading
    }

    private fun setError(message: String?) {
        baseContext.showSnack(
            view = binding.root,
            message = message,
            backgroundColor = baseContext.getColorCompat(R.color.red)
        )
    }

    private fun showProductsAndAccountName(
        products: ProductsResponse,
        account: AccountResponse
    ) = binding.run {
        showAccount(account)
        showMainBanner(products.cash)
        showSpotlights(products.spotlight)
        showProducts(products.products)
        mainMl.setVisible(true)
    }

    private fun showAccount(account: AccountResponse) = binding.run {
        profileNameTv.text = getString(
            R.string.profile_welcome_name,
            account.name
        )

        imageHelper.loadImage(
            url = account.photoUrl,
            view = accountIv,
            cropType = ImageHelper.CropType.CIRCULAR
        )
    }

    private fun showMainBanner(mainBannerDetails: CashResponse) = binding.run {
        mainBannerTitleTv.text = baseContext.setSecondTextColor(mainBannerDetails.title)
        imageHelper.loadImage(
            url = mainBannerDetails.bannerURL,
            view = mainBannerIv
        )

        mainBannerIv.setOnClickListener {
            viewModel.handleProductsIntent(
                ProductsIntent.GoToProductDetails(
                    name = mainBannerDetails.title,
                    description = mainBannerDetails.description,
                    imageUrl = mainBannerDetails.bannerURL
                )
            )
        }
    }

    private fun showSpotlights(spotlights: List<SpotlightResponse>) = binding.run {
        spotlightsAdapter.items = spotlights
    }

    private fun showProducts(products: List<ItemProductResponse>) = binding.run {
        productsAdapter.items = products
    }

    private fun openProductDetails(product: ProductDetail) {
        baseContext.showMessage(product.name)
    }

}