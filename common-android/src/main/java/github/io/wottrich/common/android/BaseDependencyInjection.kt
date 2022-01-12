package github.io.wottrich.common.android

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

abstract class BaseDependencyInjection {
    abstract fun modules(): Module
}

abstract class MultiDependencyInjection : BaseDependencyInjection() {
    abstract val featureModules: List<BaseDependencyInjection>
    override fun modules() = module {}
    operator fun invoke(): List<Module> = featureModules.map { it.modules() }
}