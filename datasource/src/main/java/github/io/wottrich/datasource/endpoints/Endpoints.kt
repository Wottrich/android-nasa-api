package github.io.wottrich.datasource.endpoints

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

object Endpoints {

    /**
     * APOD - Astronomy Picture of the Day
     *
     * One of the most popular websites at NASA is the Astronomy Picture of the Day.
     * In fact, this website is one of the most popular websites across all federal agencies.
     * It has the popular appeal of a Justin Bieber video.
     * This endpoint structures the APOD imagery and associated metadata so that it can be repurposed for other applications.
     * In addition, if the concept_tags parameter is set to True, then keywords derived from the image explanation are returned.
     * These keywords could be used as auto-generated hashtags for twitter or instagram feeds;
     * but generally help with discoverability of relevant imagery.
     * The full documentation for this API can be found in the [APOD API Github repository](https://github.com/nasa/apod-api).
     */
    const val APODBaseUrl = "https://api.nasa.gov/"
    const val APODKoinNamed = "APOD-API"

}