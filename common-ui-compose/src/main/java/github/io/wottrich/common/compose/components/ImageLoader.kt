package github.io.wottrich.common.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 13/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

@Composable
fun ImageLoaded(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    imageBuilder: ImageRequest.Builder.() -> Unit = { defaultImageRequestBuilder() }
) {
    Image(
        modifier = modifier
            .fillMaxWidth(),
        painter = rememberImagePainter(
            data = url,
            imageLoader = LocalImageLoader.current,
            builder = {
                this.apply(imageBuilder)
            }
        ),
        contentDescription = contentDescription,
    )
}

private fun ImageRequest.Builder.defaultImageRequestBuilder() {
    crossfade(true)
}