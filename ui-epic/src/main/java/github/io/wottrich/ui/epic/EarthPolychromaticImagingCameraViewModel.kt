package github.io.wottrich.ui.epic

import github.io.wottrich.data.EarthPolychromaticImagingCamera
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.domain.GetEarthPolychromaticImagingCameraUseCase
import github.io.wottrich.domain.base.BaseViewModel
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
            getEarthPolychromaticImagingCameraUseCase {
                success = {
                    _uiState.value = uiState.value.copy(
                        isLoading = false,
                        earthPolychromaticImages = it
                    )
                }
            }
        }
    }

}

data class EpicUiState(
    val isLoading: Boolean,
    val earthPolychromaticImages: List<EarthPolychromaticImagingCamera>
)

private fun initEpicUiState() = EpicUiState(
    isLoading = true,
    earthPolychromaticImages = emptyList()
)