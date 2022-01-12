package github.io.wottrich.common.android.extensions

import github.io.wottrich.resource.Resource
import github.io.wottrich.resource.Resource.Cached
import github.io.wottrich.resource.Resource.Failure
import github.io.wottrich.resource.Resource.Loading
import github.io.wottrich.resource.Resource.Success
import github.io.wottrich.screen.state.ScreenState
import github.io.wottrich.screen.state.ScreenStateCached
import github.io.wottrich.screen.state.ScreenStateFailure

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

fun <T> Resource<T>.toState(): ScreenState<T> = when (this) {
    is Failure -> ScreenState.failure<T>(
        ScreenStateFailure(
            this.throwable
        )
    )
    is Loading -> ScreenState.initial()
    is Success -> {
        val success = checkNotNull(this.data)
        ScreenState.success(success)
    }
    is Cached -> {
        val cached = checkNotNull(this.data)
        ScreenState.cached(ScreenStateCached<T>(cached))
    }
}
