package github.io.wottrich.home

import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.domain.AvailableItems
import github.io.wottrich.domain.GetAvailableItemsUseCase
import github.io.wottrich.domain.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class HomeViewModel(
    dispatchers: AppDispatchers,
    private val getAvailableItemsUseCase: GetAvailableItemsUseCase,
) : BaseViewModel(dispatchers) {

    private val _state = MutableStateFlow(HomeUiState.Initial)
    val state = _state.asStateFlow()

    init {
        launchIO {
            getAvailableItemsUseCase {
                loading = {
                    _state.value = state.value.copy(isLoading = true)
                }
                success = {
                    _state.value = state.value.copy(isLoading = false, availableItems = it)
                }
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean,
    val availableItems: List<AvailableItems>,
) {
    companion object {
        val Initial = HomeUiState(
            isLoading = true,
            availableItems = emptyList()
        )
    }
}