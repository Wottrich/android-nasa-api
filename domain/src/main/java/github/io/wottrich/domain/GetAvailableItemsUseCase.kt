package github.io.wottrich.domain

import github.io.wottrich.domain.AvailableItemType.AstronomyPictureOfTheDay
import github.io.wottrich.domain.AvailableItemType.EarthPolychromaticImagingCamera
import github.io.wottrich.domain.base.UseCase
import github.io.wottrich.domain.base.UseCase.None
import github.io.wottrich.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

enum class AvailableItemType {
    AstronomyPictureOfTheDay,
    EarthPolychromaticImagingCamera
}

data class AvailableItems(
    val title: String,
    val type: AvailableItemType
)

class GetAvailableItemsUseCase : UseCase<None, List<AvailableItems>>() {

    private val availableItems = listOf(
        AvailableItems("Astronomy Picture Of The Day", AstronomyPictureOfTheDay),
        AvailableItems("Earth Polychromatic Imaging Camera", EarthPolychromaticImagingCamera)
    )

    override suspend fun execute(params: None): Flow<Resource<List<AvailableItems>>> = flow {
        emit(Resource.success(availableItems))
    }
}