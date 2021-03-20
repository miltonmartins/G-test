package com.martins.milton.goktest.di

import androidx.lifecycle.ViewModel
import com.martins.milton.goktest.ui.products.ProductsActivity
import com.martins.milton.goktest.ui.products.ProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface ProductsModule {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    fun productsActivity(): ProductsActivity

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    fun bindViewModel(viewModel: ProductsViewModel): ViewModel
}