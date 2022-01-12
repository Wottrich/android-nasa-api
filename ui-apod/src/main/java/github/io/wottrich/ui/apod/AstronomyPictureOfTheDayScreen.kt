package github.io.wottrich.ui.apod

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import github.io.wottrich.common.compose.components.DefaultLoadingContent
import github.io.wottrich.domain.GetAstronomyPictureOfTheDayUseCase
import org.koin.androidx.compose.getViewModel

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

@Composable
fun AstronomyPictureOfTheDayScreen(
    viewModel: AstronomyPictureOfTheDayViewModel = getViewModel()
) {
    Scaffold {
        Screen(astronomyPictureOfTheDayViewModel = viewModel)
    }
}

@Composable
private fun Screen(
    astronomyPictureOfTheDayViewModel: AstronomyPictureOfTheDayViewModel
) {
    val state by astronomyPictureOfTheDayViewModel.state.collectAsState()
    val astronomyPictureOfTheDay = state.astronomyPictureOfTheDay

    if (state.isLoading) {
        APODLoading()
    } else {
        if (astronomyPictureOfTheDay != null) {
            ImageLoaded(url = astronomyPictureOfTheDay.url)
        }
    }
}

@Composable
private fun APODLoading() {
    DefaultLoadingContent()
}

@Composable
fun ImageLoaded(url: String) {
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = rememberImagePainter(
            data = url,
            imageLoader = LocalImageLoader.current,
            builder = {
                crossfade(true)
            }
        ),
        contentDescription = "avatar image",
    )
}