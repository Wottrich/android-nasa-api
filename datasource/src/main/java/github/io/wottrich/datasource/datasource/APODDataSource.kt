package github.io.wottrich.datasource.datasource

import github.io.wottrich.data.AstronomyPictureOfTheDay
import github.io.wottrich.resource.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

interface APODDataSource {
    fun loadAPOD(): Flow<Result<AstronomyPictureOfTheDay>>
}