package github.io.wottrich.domain

import github.io.wottrich.data.AstronomyPictureOfTheDay
import github.io.wottrich.datasource.api.APODEndpoint
import github.io.wottrich.domain.base.KotlinResultUseCase
import github.io.wottrich.domain.base.None

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class GetAstronomyPictureOfTheDayUseCase(
    private val apodRepository: APODEndpoint
) : KotlinResultUseCase<None, AstronomyPictureOfTheDay>() {
    override suspend fun execute(params: None): Result<AstronomyPictureOfTheDay> =
        apodRepository.loadAstronomyPictureOfTheDay()
}