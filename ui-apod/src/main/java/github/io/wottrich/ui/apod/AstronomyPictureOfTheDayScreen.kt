package github.io.wottrich.ui.apod

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import github.io.wottrich.common.compose.components.DefaultLoadingContent
import github.io.wottrich.common.compose.components.ImageLoaded
import github.io.wottrich.common.compose.theme.dimens.Dimens
import github.io.wottrich.common.compose.theme.ui.theme.NasaTheme
import github.io.wottrich.ui.apod.R.string
import org.koin.androidx.compose.getViewModel

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

private val ImageHeightSize = 400.dp

@Composable
fun AstronomyPictureOfTheDayScreen(
    onBackButton: () -> Unit,
    viewModel: AstronomyPictureOfTheDayViewModel = getViewModel()
) {
    NasaTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = onBackButton) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(
                                    id = R.string.back_button_content_description
                                )
                            )
                        }
                    },
                    title = {
                        Text(text = stringResource(id = R.string.ui_apod_top_bar_title))
                    }
                )
            }
        ) {
            Screen(astronomyPictureOfTheDayViewModel = viewModel)
        }
    }
}

@Composable
private fun Screen(
    astronomyPictureOfTheDayViewModel: AstronomyPictureOfTheDayViewModel
) {
    val state by astronomyPictureOfTheDayViewModel.state.collectAsState()
    val astronomyPictureOfTheDay = state.astronomyPictureOfTheDay

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(all = Dimens.Small.L)
    ) {
        if (state.isLoading) {
            APODLoading()
        } else {
            astronomyPictureOfTheDay?.apply {
                TitleContent(title = title)
                ImageLoaded(
                    modifier = Modifier.height(ImageHeightSize),
                    url = url,
                    contentDescription = stringResource(id = string.ui_apod_image_of_the_day)
                )
                Spacer(modifier = Modifier.height(Dimens.Small.S))
                Copyright(copyright = copyright)
                Explanation(explanation = explanation)
            }
        }
    }
}

@Composable
private fun APODLoading() {
    DefaultLoadingContent()
}

@Composable
private fun TitleContent(title: String) {
    Text(text = title, style = MaterialTheme.typography.h5)
    Spacer(modifier = Modifier.height(Dimens.Small.L))
}

@Composable
private fun Copyright(copyright: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = copyright,
            style = MaterialTheme.typography.caption
        )
    }
    Spacer(modifier = Modifier.height(Dimens.Small.S))
}

@Composable
private fun Explanation(explanation: String) {
    Text(
        text = stringResource(string.ui_apod_explanation_label_description),
        style = MaterialTheme.typography.h6
    )
    Text(
        text = explanation,
        style = MaterialTheme.typography.body1
    )
}