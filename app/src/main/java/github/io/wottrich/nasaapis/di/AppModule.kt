package github.io.wottrich.nasaapis.di

import github.io.wottrich.datasource.di.dataSourceKoinModule
import org.koin.core.module.Module

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

/*
listOf(
    dataSourceKoinModule,
    splashModule,
    homeModule
)
 */

val appModule = mutableListOf<Module>().apply {
    add(splashModule)
    add(dataSourceKoinModule)
    addAll(DomainModules())
    addAll(FeatureModules())
}.toList()