package github.io.wottrich.datasource.api

import github.io.wottrich.data.EarthPolychromaticImagingCamera
import github.io.wottrich.resource.Resource
import retrofit2.http.GET

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 13/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

interface EPICEndpoint {

    @GET("api/natural")
    suspend fun loadEarthPolychromaticImagingCameraList(): Resource<List<EarthPolychromaticImagingCamera>>

}