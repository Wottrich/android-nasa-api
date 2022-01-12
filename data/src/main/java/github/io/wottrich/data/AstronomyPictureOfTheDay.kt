package github.io.wottrich.data

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */
 
data class AstronomyPictureOfTheDay(
    val copyright: String,
    val data: String,
    val explanation: String,
    private val media_type: String,
    val title: String,
    val url: String
) {
    val mediaType: String = media_type
}