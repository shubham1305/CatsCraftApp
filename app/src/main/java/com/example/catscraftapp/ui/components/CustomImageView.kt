package com.example.catscraftapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.catscraftapp.R

@Composable
fun CustomImageView(
    modifier: Modifier,
    imageUrl: String?,
    scale: ContentScale
) {
    val context = LocalContext.current

    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(
            remember(imageUrl) {
                ImageRequest.Builder(context)
                    .data(imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .build()
            }
        ),
        contentDescription = null,
        contentScale = scale
    )
}