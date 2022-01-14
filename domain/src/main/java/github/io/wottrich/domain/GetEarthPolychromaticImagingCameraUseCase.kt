package github.io.wottrich.domain

import github.io.wottrich.data.EarthPolychromaticImagingCamera
import github.io.wottrich.datasource.datasource.EPICDatasource
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
    private val datasource: EPICDatasource
) : UseCase<UseCase.None, List<EarthPolychromaticImagingCamera>>() {
    override suspend fun execute(params: None): Flow<Resource<List<EarthPolychromaticImagingCamera>>> {
        return datasource.loadEpic()
    }
}