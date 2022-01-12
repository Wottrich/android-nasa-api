package github.io.wottrich.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import github.io.wottrich.common.compose.components.DefaultLoadingContent
import github.io.wottrich.common.compose.components.ListItemStartTextContent
import github.io.wottrich.common.compose.components.RowDefaults
import github.io.wottrich.common.compose.theme.dimens.Dimens.Small
import github.io.wottrich.common.compose.theme.ui.theme.NasaTheme
import github.io.wottrich.domain.AvailableItemType
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
        Scaffold {
            Screen(homeViewModel = viewModel, onItemClicked = onItemClicked)
        }
    }
}

@Composable
private fun Screen(homeViewModel: HomeViewModel, onItemClicked: (AvailableItemType) -> Unit) {
    val state by homeViewModel.state.collectAsState()

    if (state.isLoading) {
        HomeLoading()
    } else {
        HomeContent(state, onItemClicked)
    }
}

@Composable
private fun HomeLoading() {
    DefaultLoadingContent()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeContent(state: HomeUiState, onItemClicked: (AvailableItemType) -> Unit) {
    LazyColumn {
        items(state.availableItems) { item ->
            Surface(
                modifier = Modifier.padding(all = Small.S).clickable { onItemClicked(item.type) },
                shape = RoundedCornerShape(size = Small.S)
            ) {
                ListItem(
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