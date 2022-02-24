package github.io.wottrich.domain.base

fun <T> Result<T>.toDsl(
    success: (T?) -> Unit,
    failure: ((Throwable) -> Unit)? = null
): Result<T> {
    return this.also {
        it.onSuccess(success)
        it.onFailure { exception ->  failure?.invoke(exception) }
    }
}

suspend fun <Params, ReturnType> KotlinResultUseCase<Params, ReturnType>.onLoadingResult(
    block: (isLoading: Boolean) -> Unit
): Result<ReturnType> {
    block(true)
    val result = this()
    block(false)
    return result
}