package github.io.wottrich.nasaapis.di

import github.io.wottrich.common.android.BaseDependencyInjection
import github.io.wottrich.common.android.MultiDependencyInjection
import github.io.wottrich.domain.di.DomainInjection

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

object DomainModules : MultiDependencyInjection() {
    override val featureModules: List<BaseDependencyInjection> = listOf(
        DomainInjection
    )
}