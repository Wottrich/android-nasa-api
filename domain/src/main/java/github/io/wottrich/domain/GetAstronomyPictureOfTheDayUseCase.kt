package github.io.wottrich.domain

import github.io.wottrich.data.AstronomyPictureOfTheDay
import github.io.wottrich.datasource.datasource.APODDataSource
import github.io.wottrich.domain.base.UseCase
import github.io.wottrich.resource.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class GetAstronomyPictureOfTheDayUseCase(
    private val apodDataSource: APODDataSource
) : UseCase<UseCase.None, AstronomyPictureOfTheDay>() {
    override suspend fun execute(params: None): Flow<Resource<AstronomyPictureOfTheDay>> =
        apodDataSource.loadAPOD()
}