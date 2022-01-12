package github.io.wottrich.nasaapis.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.io.wottrich.common.compose.flow.SingleShotEventBus
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 07/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class SplashViewModel(
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val _effects = SingleShotEventBus<SplashUiEffects>()
    val effects: Flow<SplashUiEffects> = _effects.events

    fun onSideEffect() {
        viewModelScope.launch(dispatchers.io) {
            delay(SPLASH_DELAY)
            _effects.emit(SplashUiEffects.FinishSplash)
        }
    }

    companion object {
        const val SPLASH_DELAY = 1000L
    }
}

sealed class SplashUiEffects {
    object FinishSplash : SplashUiEffects()
}