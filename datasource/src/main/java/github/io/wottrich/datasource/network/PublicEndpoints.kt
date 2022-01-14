package github.io.wottrich.datasource.network

import github.io.wottrich.datasource.api.APODEndpoint
import github.io.wottrich.datasource.api.EPICEndpoint
import github.io.wottrich.datasource.endpoints.Endpoints
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

val Scope.APODRetrofitInstance: APODEndpoint
    get() = Network.buildRetrofit(get(named(Endpoints.APODKoinNamed))).create(APODEndpoint::class.java)

val Scope.EPICRetrofitInstance: EPICEndpoint
    get() = Network.buildRetrofit(get(named(Endpoints.EPICKoinNamed))).create(EPICEndpoint::class.java)