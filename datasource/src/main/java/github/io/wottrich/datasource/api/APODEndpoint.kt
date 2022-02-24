package github.io.wottrich.datasource.api

import github.io.wottrich.data.AstronomyPictureOfTheDay
import retrofit2.http.GET

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

interface APODEndpoint {

    @GET("planetary/apod?api_key=DEMO_KEY")
    suspend fun loadAstronomyPictureOfTheDay(): Result<AstronomyPictureOfTheDay>

}