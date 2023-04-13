package github.io.wottrich.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Result<T>.toDsl(
    success: (T?) -> Unit,
    failure: ((Throwable) -> Unit)? = null
): Result<T> {
    return this.also {
        it.onSuccess(success)
        it.onFailure { exception ->  failure?.invoke(exception) }
    }
}

suspend fun <Params, ReturnType> UseCase<Params, ReturnType>.onLoadingResult(
    block: (isLoading: Boolean) -> Unit
): ReturnType {
    block(true)
    val result = this()
    block(false)
    return result
}

suspend fun <Params, ReturnType> KotlinResultUseCase<Params, ReturnType>.onLoadingResult(
    block: (isLoading: Boolean) -> Unit
): Result<ReturnType> {
    return onLoadingResult<Params, Result<ReturnType>>(block)
}

suspend fun <Params, ReturnType> FlowableUseCase<Params, ReturnType>.collect(
    loadingBlock: (isLoading: Boolean) -> Unit = {},
    collect: (Result<ReturnType>) -> Unit
) {
    return onLoadingResult<Params, Flow<Result<ReturnType>>>(loadingBlock).collect { collect(it) }
}