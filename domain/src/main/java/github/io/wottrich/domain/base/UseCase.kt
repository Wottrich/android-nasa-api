package github.io.wottrich.domain.base

import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.resource.Resource
import github.io.wottrich.resource.Resource.Cached
import github.io.wottrich.resource.Resource.Failure
import github.io.wottrich.resource.Resource.Loading
import github.io.wottrich.resource.Resource.Success
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
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


class ResultDsl<T> {
    var loading: () -> Unit = {}
    var success: (T) -> Unit = {}
    var cached: (T) -> Unit = {}
    var error: (Throwable) -> Unit = {}
}

fun <T> Resource<T>.toDsl(resultDsl: ResultDsl<T>) {
    when (this) {
        is Cached -> {
            val data = checkNotNull(this.data)
            resultDsl.cached(data)
        }
        is Failure -> resultDsl.error(this.throwable)
        is Loading -> resultDsl.loading()
        is Success -> {
            val data = checkNotNull(this.data)
            resultDsl.success(data)
        }
    }
}

abstract class UseCase<Params, ReturnType> where ReturnType : Any? {

    abstract suspend fun execute(params: Params): Flow<Resource<ReturnType>>

    suspend operator fun invoke(
        params: Params,
        resultDsl: ResultDsl<ReturnType>.() -> Unit = {}
    ) {
        if (params == null) {
            throw Exception("Params is required")
        }
        execute(params).collect {
            val result = ResultDsl<ReturnType>().apply(resultDsl)
            it.toDsl(result)
        }
    }

    @Suppress("UNCHECKED_CAST")
    suspend operator fun invoke(
        resultDsl: ResultDsl<ReturnType>.() -> Unit = {}
    ) {
        execute(None() as Params).collect {
            val result = ResultDsl<ReturnType>().apply(resultDsl)
            it.toDsl(result)
        }
    }

    class None
}

private suspend fun <T> CoroutineContext.runSuspendBlock(
    block: suspend () -> Flow<Resource<T>>
) = withContext(this) { block() }