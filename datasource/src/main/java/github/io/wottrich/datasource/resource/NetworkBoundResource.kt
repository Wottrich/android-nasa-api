package github.io.wottrich.datasource.resource

import github.io.wottrich.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class NetworkBoundResource<T>(
    private val getFromDatabase: (suspend () -> T?)? = null,
    private val saveCallResults: (suspend (item: T) -> Unit)? = null,
    private val call: suspend () -> Result<T>
) {

    fun build(): Flow<Result<T>> {
        return flow {
            //emit(Resource.loading())
            fetchFromDatabase()
            fetchFromNetwork()
        }
    }

    private suspend fun FlowCollector<Result<T>>.fetchFromDatabase() {
        val value = getFromDatabase?.invoke()
        value?.let { databaseValue ->
            emit(Result.success(databaseValue))
        }
    }

    private suspend fun FlowCollector<Result<T>>.fetchFromNetwork() {
        val result = call()
        return when {
            result.isSuccess -> {
                val data = checkNotNull(result.getOrNull())
                saveCallResults?.invoke(data)
                emit(Result.success(data))
            }
            else -> {
                emit(result)
            }
        }
    }

}