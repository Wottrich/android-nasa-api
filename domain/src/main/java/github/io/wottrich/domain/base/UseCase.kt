package github.io.wottrich.domain.base

import github.io.wottrich.resource.Resource
import github.io.wottrich.resource.Resource.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

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
    var error: (Throwable?) -> Unit = {}
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

class None

abstract class KotlinResultUseCase<Params, ReturnType> : UseCase<Params, Result<ReturnType>> {
    abstract override suspend fun execute(params: Params): Result<ReturnType>
}

interface UseCase<Params, ReturnType> where ReturnType : Any? {

    suspend fun execute(params: Params): ReturnType

    suspend operator fun invoke(params: Params): ReturnType {
        if (params == null) {
            throw Exception("Params is required")
        }
        return execute(params)
    }

    @Suppress("UNCHECKED_CAST")
    suspend operator fun invoke(): ReturnType {
        return execute(None() as Params)
    }
}