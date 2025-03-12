package dev.psegerfast.ikeashoppingapp.di

import androidx.room.Room
import dev.psegerfast.ikeashoppingapp.cart.data.database.AppDatabase
import dev.psegerfast.ikeashoppingapp.cart.data.repository.DefaultCartRepository
import dev.psegerfast.ikeashoppingapp.cart.domain.repository.CartRepository
import dev.psegerfast.ikeashoppingapp.cart.domain.use_case.AddToCartUseCase
import dev.psegerfast.ikeashoppingapp.cart.domain.use_case.CalculateCartTotalUseCase
import dev.psegerfast.ikeashoppingapp.cart.domain.use_case.CalculateNumberOfItemsUseCase
import dev.psegerfast.ikeashoppingapp.cart.domain.use_case.CartUseCases
import dev.psegerfast.ikeashoppingapp.cart.domain.use_case.ClearCartUseCase
import dev.psegerfast.ikeashoppingapp.cart.domain.use_case.ClearProductFromCartUseCase
import dev.psegerfast.ikeashoppingapp.cart.domain.use_case.GetProductsInCartUseCase
import dev.psegerfast.ikeashoppingapp.cart.domain.use_case.RemoveSingleProductFromCartUseCase
import dev.psegerfast.ikeashoppingapp.cart.presentation.CartViewModel
import dev.psegerfast.ikeashoppingapp.product.data.io.JsonProductsDataSource
import dev.psegerfast.ikeashoppingapp.product.data.io.ProductsDataSource
import dev.psegerfast.ikeashoppingapp.product.data.repository.DefaultProductRepository
import dev.psegerfast.ikeashoppingapp.product.domain.ProductRepository
import dev.psegerfast.ikeashoppingapp.product.domain.use_case.GetProductsUseCase
import dev.psegerfast.ikeashoppingapp.product.presentation.product_list.ProductListViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
val appModule = module {
    single<Json> {
        Json {
            this.ignoreUnknownKeys = true
        }
    }

    cartUseCases()
    productUseCases()

    singleOf(::JsonProductsDataSource).bind<ProductsDataSource>()
    singleOf(::DefaultCartRepository).bind<CartRepository>()
    singleOf(::DefaultProductRepository).bind<ProductRepository>()

    single<AppDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = AppDatabase::class.java,
            name = "cart-database",
        ).build()
    }
    single { get<AppDatabase>().cartDao() }

    viewModelOf(::ProductListViewModel)
    viewModelOf(::CartViewModel)
}

private fun Module.cartUseCases() {
    singleOf(::AddToCartUseCase)
    singleOf(::CalculateCartTotalUseCase)
    singleOf(::ClearCartUseCase)
    singleOf(::ClearProductFromCartUseCase)
    singleOf(::GetProductsInCartUseCase)
    singleOf(::RemoveSingleProductFromCartUseCase)
    singleOf(::CalculateNumberOfItemsUseCase)
    singleOf(::CartUseCases)
}

private fun Module.productUseCases() {
    singleOf(::GetProductsUseCase)
}