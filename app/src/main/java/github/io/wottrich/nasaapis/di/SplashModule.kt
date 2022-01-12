package github.io.wottrich.nasaapis.di

import github.io.wottrich.nasaapis.presentation.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 07/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */



val splashModule = module {
    viewModel { SplashViewModel(get()) }
}