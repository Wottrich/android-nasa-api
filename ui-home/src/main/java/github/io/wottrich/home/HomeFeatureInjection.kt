package github.io.wottrich.home

import github.io.wottrich.common.android.BaseDependencyInjection
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

object HomeFeatureInjection : BaseDependencyInjection() {
    override fun modules() = module {
        viewModel { HomeViewModel(get(), get()) }
    }
}