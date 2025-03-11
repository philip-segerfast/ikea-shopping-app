package dev.psegerfast.ikeashoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.psegerfast.ikeashoppingapp.ui.theme.IkeaShoppingAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: ShoppingListViewModel by viewModels()
            val products by viewModel.products.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.fetchProducts()
            }

            IkeaShoppingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(Modifier.padding(innerPadding)) {
                        items(
                            items = products,
                            key = { it.id },
                            contentType = { it::class.simpleName }
                        ) { item ->
                            Column {
                                Text("Name: ${ item.name }")
                                Text("Price: ${ item.price.value } ${ item.price.currency }")
                                Text("Type: ${item::class.simpleName}")
                                Text("Image: ${item.imageUrl}")
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IkeaShoppingAppTheme {
        Greeting("Android")
    }
}