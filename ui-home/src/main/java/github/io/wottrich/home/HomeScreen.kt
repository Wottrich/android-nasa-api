package github.io.wottrich.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import github.io.wottrich.common.compose.components.DefaultLoadingContent
import github.io.wottrich.common.compose.components.ListItemStartTextContent
import github.io.wottrich.common.compose.components.RowDefaults
import github.io.wottrich.common.compose.theme.dimens.Dimens.Small
import github.io.wottrich.common.compose.theme.ui.theme.NasaTheme
import github.io.wottrich.domain.AvailableItemType
import github.io.wottrich.home.R.string
import org.koin.androidx.compose.getViewModel

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

@Composable
fun HomeScreen(
    onItemClicked: (AvailableItemType) -> Unit,
    viewModel: HomeViewModel = getViewModel()
) {
    NasaTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = string.app_name))
                    }
                )
            }
        ) {
            Screen(homeViewModel = viewModel, onItemClicked = onItemClicked)
        }
    }
}

@Composable
private fun Screen(homeViewModel: HomeViewModel, onItemClicked: (AvailableItemType) -> Unit) {
    val state by homeViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(all = Small.L)
    ) {
        if (state.isLoading) {
            HomeLoading()
        } else {
            HomeContent(state, onItemClicked)
        }
    }
}

@Composable
private fun HomeLoading() {
    DefaultLoadingContent()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeContent(state: HomeUiState, onItemClicked: (AvailableItemType) -> Unit) {
    val roundedCornerShape = RoundedCornerShape(size = Small.S)
    LazyColumn {
        items(state.availableItems) { item ->
            Surface(
                modifier = Modifier.padding(all = Small.S),
                shape = roundedCornerShape
            ) {
                ListItem(
                    modifier = Modifier
                        .clip(roundedCornerShape)
                        .clickable { onItemClicked(item.type) },
                    trailing = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null
                        )
                    },
                    text = {
                        ListItemStartTextContent(
                            primary = RowDefaults.description(text = item.title)
                        )
                    }
                )
            }
        }
    }
}