package github.io.wottrich.domain

import github.io.wottrich.data.AstronomyPictureOfTheDay
import github.io.wottrich.datasource.api.APODEndpoint
import github.io.wottrich.datasource.datasource.APODDataSource
import github.io.wottrich.domain.base.FlowableUseCase
import github.io.wottrich.domain.base.KotlinResultUseCase
import github.io.wottrich.domain.base.None
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class GetAstronomyPictureOfTheDayUseCase(
    private val apodRepository: APODDataSource
) : FlowableUseCase<None, AstronomyPictureOfTheDay>() {
    override suspend fun execute(params: None): Flow<Result<AstronomyPictureOfTheDay>> =
        apodRepository.loadAPOD()
}