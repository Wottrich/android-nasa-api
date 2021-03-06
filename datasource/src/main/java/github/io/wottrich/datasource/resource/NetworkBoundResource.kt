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
    private val call: suspend () -> Resource<T>
) {

    fun build(): Flow<Resource<T>> {
        return flow {
            emit(Resource.loading())
            fetchFromDatabase()
            fetchFromNetwork()
        }
    }

    private suspend fun FlowCollector<Resource<T>>.fetchFromDatabase() {
        val value = getFromDatabase?.invoke()
        value?.let { databaseValue ->
            emit(Resource.cached(databaseValue))
        }
    }

    private suspend fun FlowCollector<Resource<T>>.fetchFromNetwork() {
        return when (val result = call()) {
            is Resource.Success -> {
                val data = checkNotNull(result.data)
                saveCallResults?.invoke(data)
                emit(Resource.success(data))
            }
            else -> {
                emit(result)
            }
        }
    }

}