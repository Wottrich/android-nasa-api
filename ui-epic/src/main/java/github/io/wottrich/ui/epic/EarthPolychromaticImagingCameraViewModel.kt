package github.io.wottrich.ui.epic

import github.io.wottrich.data.EarthPolychromaticImagingCamera
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.domain.GetEarthPolychromaticImagingCameraUseCase
import github.io.wottrich.domain.base.BaseViewModel
import github.io.wottrich.domain.base.collect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 13/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class EarthPolychromaticImagingCameraViewModel(
    dispatchers: AppDispatchers,
    private val getEarthPolychromaticImagingCameraUseCase: GetEarthPolychromaticImagingCameraUseCase
) : BaseViewModel(dispatchers) {

    private val _uiState = MutableStateFlow(initEpicUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchIO {
            loadEarthPolychromaticImagingCamera()
        }
    }

    fun onTryAgain() {
        launchIO {
            loadEarthPolychromaticImagingCamera()
        }
    }

    private suspend fun loadEarthPolychromaticImagingCamera() {
        getEarthPolychromaticImagingCameraUseCase.collect(
            loadingBlock = {
                _uiState.value = uiState.value.copy(isLoading = it, hasError = false)
            },
            collect = { result ->
                result.onSuccess {
                    _uiState.value = uiState.value.copy(
                        earthPolychromaticImages = it,
                        hasError = false
                    )
                }.onFailure {
                    _uiState.value = uiState.value.copy(hasError = true)
                }
            }
        )
    }

}

data class EpicUiState(
    val isLoading: Boolean,
    val earthPolychromaticImages: List<EarthPolychromaticImagingCamera>,
    val hasError: Boolean
)

private fun initEpicUiState() = EpicUiState(
    isLoading = true,
    earthPolychromaticImages = emptyList(),
    hasError = false
)