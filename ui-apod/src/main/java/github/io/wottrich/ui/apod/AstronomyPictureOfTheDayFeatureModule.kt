package github.io.wottrich.ui.apod

import github.io.wottrich.common.android.BaseDependencyInjection
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

object AstronomyPictureOfTheDayFeatureModule : BaseDependencyInjection() {
    override fun modules(): Module = module {
        viewModel { AstronomyPictureOfTheDayViewModel(get(), get()) }
    }
}