package github.io.wottrich.ui.apod

import github.io.wottrich.data.AstronomyPictureOfTheDay
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.domain.GetAstronomyPictureOfTheDayUseCase
import github.io.wottrich.domain.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class AstronomyPictureOfTheDayViewModel(
    dispatchers: AppDispatchers,
    private val getAstronomyPictureOfTheDayUseCase: GetAstronomyPictureOfTheDayUseCase
) : BaseViewModel(dispatchers) {

    private val _state = MutableStateFlow(AstronomyPictureOfTheDayUiState.Initial)
    val state = _state.asStateFlow()

    init {
        launchIO {
            getAstronomyPictureOfTheDayUseCase {
                loading = {
                    _state.value = state.value.copy(isLoading = true)
                }
                success = {
                    _state.value = state.value.copy(
                        isLoading = false,
                        astronomyPictureOfTheDay = it
                    )
                }
            }
        }
    }
}

data class AstronomyPictureOfTheDayUiState(
    val isLoading: Boolean,
    val astronomyPictureOfTheDay: AstronomyPictureOfTheDay?
) {
    companion object {
        val Initial = AstronomyPictureOfTheDayUiState(
            isLoading = true,
            astronomyPictureOfTheDay = null
        )
    }
}