package dev.psegerfast.ikeashoppingapp.product.presentation.product_list.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import dev.psegerfast.ikeashoppingapp.R
import dev.psegerfast.ikeashoppingapp.product.domain.Product

@Composable
fun ProductPicture(
    product: Product,
    modifier: Modifier = Modifier,
) {
    // I could probably also just have used AsyncImage for all this

    val fallbackUrl = when(product) {
        is Product.Chair -> "https://www.ikea.com/global/assets/range-categorisation/images/product/dining-chairs-25219.jpeg?imwidth=160"
        is Product.Couch -> "https://www.ikea.com/global/assets/range-categorisation/images/product/sofas-armchairs-700640.jpeg?imwidth=160"
    }

    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    val painter = rememberAsyncImagePainter(
        model = product.imageUrl,
        onSuccess = {
            imageLoadResult =
                if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                    Result.success(it.painter)
                } else {
                    Result.failure(Exception("Invalid image size"))
                }
        },
        onError = {
            it.result.throwable.printStackTrace()
            imageLoadResult = Result.failure(it.result.throwable)
        }
    )

    Surface(modifier) {
        AnimatedContent(imageLoadResult, contentAlignment = Alignment.Center) { result ->
            when (result?.isSuccess) {
                null -> LoadingPlaceholder()
                true -> {
                    Image(
                        painter = painter,
                        contentDescription = product.name,
                        contentScale = if (result.isSuccess) {
                            ContentScale.Crop
                        } else {
                            ContentScale.Fit
                        },
                        modifier = Modifier
                            .aspectRatio(
                                ratio = 1f,
                                matchHeightConstraintsFirst = true
                            )
                    )
                }
                false -> {
                    // Fallback picture
                    Box(contentAlignment = Alignment.Center) {
                        AsyncImage(
                            model = fallbackUrl,
                            contentDescription = null,
                            alpha = 0.5f
                        )
                        Icon(
                            modifier = Modifier.alpha(0.5f),
                            painter = painterResource(R.drawable.baseline_broken_image_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }

        }
    }
}