package github.io.wottrich.domain

import github.io.wottrich.data.EarthPolychromaticImagingCamera
import github.io.wottrich.datasource.api.EPICEndpoint
import github.io.wottrich.datasource.datasource.EPICDatasource
import github.io.wottrich.domain.base.KotlinResultUseCase
import github.io.wottrich.domain.base.None
import github.io.wottrich.domain.base.UseCase
import github.io.wottrich.resource.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 13/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class GetEarthPolychromaticImagingCameraUseCase(
    private val epicEndpoint: EPICEndpoint
) : KotlinResultUseCase<None, List<EarthPolychromaticImagingCamera>>() {
    override suspend fun execute(params: None): Result<List<EarthPolychromaticImagingCamera>> {
        return epicEndpoint.loadEarthPolychromaticImagingCameraList()
    }
}