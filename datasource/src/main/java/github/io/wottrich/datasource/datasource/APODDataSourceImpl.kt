package github.io.wottrich.datasource.datasource

import github.io.wottrich.data.AstronomyPictureOfTheDay
import github.io.wottrich.datasource.api.APODEndpoint
import github.io.wottrich.datasource.resource.NetworkBoundResource
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

class APODDataSourceImpl(
    private val apodRepository: APODEndpoint
) : APODDataSource{
    override fun loadAPOD(): Flow<Resource<AstronomyPictureOfTheDay>> {
        return NetworkBoundResource(
            call = { apodRepository.loadAstronomyPictureOfTheDay() }
        ).build()
    }
}