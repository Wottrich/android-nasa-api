package github.io.wottrich.domain.di

import github.io.wottrich.common.android.BaseDependencyInjection
import github.io.wottrich.domain.GetAstronomyPictureOfTheDayUseCase
import github.io.wottrich.domain.GetAvailableItemsUseCase
import github.io.wottrich.domain.GetEarthPolychromaticImagingCameraUseCase
import org.koin.dsl.module

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

object DomainInjection : BaseDependencyInjection() {
    override fun modules() = module {
        factory { GetAvailableItemsUseCase() }
        factory { GetAstronomyPictureOfTheDayUseCase(get()) }
        factory { GetEarthPolychromaticImagingCameraUseCase(get()) }
    }
}