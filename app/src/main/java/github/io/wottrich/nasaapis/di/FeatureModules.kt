package github.io.wottrich.nasaapis.di

import github.io.wottrich.common.android.BaseDependencyInjection
import github.io.wottrich.common.android.MultiDependencyInjection
import github.io.wottrich.home.HomeFeatureInjection
import github.io.wottrich.ui.apod.AstronomyPictureOfTheDayFeatureModule

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

object FeatureModules : MultiDependencyInjection() {
    override val featureModules: List<BaseDependencyInjection> = listOf(
        HomeFeatureInjection,
        AstronomyPictureOfTheDayFeatureModule
    )
}