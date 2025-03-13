package dev.psegerfast.ikeashoppingapp.app

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.psegerfast.ikeashoppingapp.app.ui.theme.IkeaShoppingAppTheme
import dev.psegerfast.ikeashoppingapp.cart.presentation.CartAction
import dev.psegerfast.ikeashoppingapp.cart.presentation.CartViewModel
import dev.psegerfast.ikeashoppingapp.cart.presentation.components.Cart
import dev.psegerfast.ikeashoppingapp.product.presentation.product_list.ProductListViewModel
import dev.psegerfast.ikeashoppingapp.product.presentation.product_list.components.ProductList
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val productsViewModel: ProductListViewModel = koinViewModel()
    val productListUiState by productsViewModel.productListState.collectAsStateWithLifecycle()

    val cartViewModel: CartViewModel = koinViewModel()
    val cartUiState by cartViewModel.cartState.collectAsStateWithLifecycle()

    val showBottomSheet by remember { derivedStateOf { cartUiState.products.isNotEmpty() } }
    var bottomSheetHandleHeight by remember { mutableStateOf(0.dp) }
    var bottomSheetPeekHeight by remember { mutableStateOf(0.dp) }

    val bottomSheetStateScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            confirmValueChange = { newValue ->
                when(newValue) {
                    SheetValue.Hidden -> !showBottomSheet
                    SheetValue.Expanded -> true
                    SheetValue.PartiallyExpanded -> true
                }
            },
            skipHiddenState = false
        )
    )

    LaunchedEffect(showBottomSheet, bottomSheetPeekHeight) {
        if(showBottomSheet) {
            bottomSheetPeekHeight = bottomSheetHandleHeight
            bottomSheetStateScaffoldState.bottomSheetState.show()
        } else {
            bottomSheetPeekHeight = 0.dp
            bottomSheetStateScaffoldState.bottomSheetState.hide()
        }
    }

    IkeaShoppingAppTheme {
        BottomSheetScaffold(
            sheetContent = {
                Cart(
                    state = cartUiState,
                    onAction = cartViewModel::onAction,
                )
            },
            scaffoldState = bottomSheetStateScaffoldState,
            sheetPeekHeight = bottomSheetPeekHeight,
            sheetShape = MaterialTheme.shapes.extraLarge.copy(bottomStart = CornerSize(0.dp), bottomEnd = CornerSize(0.dp)),
            sheetDragHandle = {
                BottomSheetDragHandle(
                    onHeightSet = { bottomSheetHandleHeight = it },
                    totalPrice = cartUiState.valueTotal,
                    itemCount = cartUiState.itemCount,
                    onClearCart = { cartViewModel.onAction(CartAction.ClearCart) },
                    shape = MaterialTheme.shapes.extraLarge
                )
            },
            sheetShadowElevation = 8.dp,
            content = { paddingValues ->
                ProductList(
                    productListUiState = productListUiState,
                    onAddToCart = { cartViewModel.onAction(CartAction.AddToCart(it)) },
                    modifier = Modifier.padding(paddingValues)
                )
            }
        )
    }
}
