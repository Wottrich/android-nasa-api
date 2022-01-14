package github.io.wottrich.datasource.di

import github.io.wottrich.datasource.datasource.APODDataSource
import github.io.wottrich.datasource.datasource.APODDataSourceImpl
import github.io.wottrich.datasource.datasource.EPICDatasource
import github.io.wottrich.datasource.datasource.EPICDatasourceImpl
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.endpoints.Endpoints
import github.io.wottrich.datasource.network.APODRetrofitInstance
import github.io.wottrich.datasource.network.EPICRetrofitInstance
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
    factory<EPICDatasource> { EPICDatasourceImpl(get()) }
}

private fun Module.apis() {
    single { APODRetrofitInstance }
    single { EPICRetrofitInstance }
}

private fun Module.endpoints() {
    single(named(Endpoints.APODKoinNamed)) { Endpoints.APODBaseUrl }
    single(named(Endpoints.EPICKoinNamed)) { Endpoints.EPICBaseUrl }
}

private fun Module.appDispatchers() {
    single { AppDispatchers() }
}