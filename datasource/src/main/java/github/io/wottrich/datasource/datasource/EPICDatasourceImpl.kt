package github.io.wottrich.datasource.datasource

import github.io.wottrich.data.EarthPolychromaticImagingCamera
import github.io.wottrich.datasource.api.EPICEndpoint
import github.io.wottrich.datasource.resource.NetworkBoundResource
import github.io.wottrich.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 13/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class EPICDatasourceImpl(
    private val epicEndpoint: EPICEndpoint
) : EPICDatasource {
    override fun loadEpic(): Flow<Result<List<EarthPolychromaticImagingCamera>>> {
        return NetworkBoundResource(
            call = { epicEndpoint.loadEarthPolychromaticImagingCameraList() }
        ).build()
    }
}