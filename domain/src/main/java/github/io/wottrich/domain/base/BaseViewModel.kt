package github.io.wottrich.domain.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

abstract class BaseViewModel(
    private val dispatchers: AppDispatchers
) : ViewModel() {

    fun io() = viewModelScope.coroutineContext + dispatchers.io

    fun main() = viewModelScope.coroutineContext + dispatchers.main

    fun launchMain(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(main(), start, block)

    fun launchIO(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(io(), start, block)

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, start, block)

    suspend fun withMainContext(block: suspend CoroutineScope.() -> Unit) {
        withContext(dispatchers.main, block)
    }
}