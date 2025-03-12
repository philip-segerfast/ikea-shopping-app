package dev.psegerfast.ikeashoppingapp.cart.domain.use_case

data class CartUseCases(
    val getProductsInCartFlow: GetProductsInCartUseCase,
    val addToCart: AddToCartUseCase,
    val removeSingleFromCart: RemoveSingleProductFromCartUseCase,
    val clearProductFromCart: ClearProductFromCartUseCase,
    val clearCart: ClearCartUseCase,
    val calculateCartTotal: CalculateCartTotalUseCase,
    val calculateNumberOfItems: CalculateNumberOfItemsUseCase,
)
