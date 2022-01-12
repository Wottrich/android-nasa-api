package github.io.wottrich.datasource.di

import github.io.wottrich.datasource.datasource.APODDataSource
import github.io.wottrich.datasource.datasource.APODDataSourceImpl
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.endpoints.Endpoints
import github.io.wottrich.datasource.network.APODRetrofitInstance
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

val dataSourceKoinModule = module {
    appDispatchers()
    endpoints()
    apis()
    datasource()
}

private fun Module.datasource() {
    factory<APODDataSource> { APODDataSourceImpl(get()) }
}

private fun Module.apis() {
    single { APODRetrofitInstance }
}

private fun Module.endpoints() {
    single(named(Endpoints.APODKoinNamed)) { Endpoints.APODBaseUrl }
}

private fun Module.appDispatchers() {
    single { AppDispatchers() }
}