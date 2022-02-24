package github.io.wottrich.ui.epic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import github.io.wottrich.common.compose.components.DefaultErrorComponent
import github.io.wottrich.common.compose.components.DefaultLoadingContent
import github.io.wottrich.common.compose.components.ImageLoaded
import github.io.wottrich.common.compose.theme.dimens.Dimens
import github.io.wottrich.common.compose.theme.ui.theme.NasaTheme
import github.io.wottrich.data.EarthPolychromaticImagingCamera
import org.koin.androidx.compose.getViewModel

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 13/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

private val ImageHeightSize = 380.dp

@Composable
fun EarthPolychromaticImagingCameraScreen(
    onBackButton: () -> Unit,
    viewModel: EarthPolychromaticImagingCameraViewModel = getViewModel()
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
                        Text(text = stringResource(id = R.string.ui_epic_top_bar_title))
                    }
                )
            }
        ) {
            Screen(earthPolychromaticImagingCameraViewModel = viewModel)
        }
    }
}

@Composable
private fun Screen(
    earthPolychromaticImagingCameraViewModel: EarthPolychromaticImagingCameraViewModel
) {

    val state by earthPolychromaticImagingCameraViewModel.uiState.collectAsState()

    if (state.hasError) {
        DefaultErrorComponent {
            earthPolychromaticImagingCameraViewModel.onTryAgain()
        }
    } else {
        EpicContent(state)
    }
}

@Composable
fun EpicContent(state: EpicUiState) {
    if (state.isLoading) {
        DefaultLoadingContent()
    } else {
        LazyColumn(
            content = {
                items(state.earthPolychromaticImages) { item ->
                    val content: @Composable () -> Unit = remember(key1 = item.identifier) {
                        { EarthPolychromaticImageContent(item = item) }
                    }
                    content()
                }
            }
        )
    }
}

@Composable
private fun EarthPolychromaticImageContent(item: EarthPolychromaticImagingCamera) {
    Column(
        modifier = Modifier.padding(all = Dimens.Small.L)
    ) {
        Text(
            text = item.date,
            style = MaterialTheme.typography.h6
        )
        ImageLoaded(
            modifier = Modifier.height(ImageHeightSize),
            url = item.getImageUrl(),
        )
        Text(
            text = item.caption,
            style = MaterialTheme.typography.caption
        )
    }
}