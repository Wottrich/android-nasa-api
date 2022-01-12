package github.io.wottrich.datasource.dispatchers

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

data class AppDispatchers(
    val main: CoroutineContext = Dispatchers.Main,
    val io: CoroutineContext = Dispatchers.IO
)