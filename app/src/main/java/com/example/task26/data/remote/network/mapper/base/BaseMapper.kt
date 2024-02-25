package com.example.task26.data.remote.network.mapper.base

import com.example.task26.data.remote.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <Dto : Any, Domain : Any> Flow<Resource<Dto>>.asResource(
    onSuccess: suspend (Dto) -> Domain,
): Flow<Resource<Domain>> {
    return this.map {
        when (it) {
            is Resource.Success -> Resource.Success(result = onSuccess.invoke(it.result))
            is Resource.Failure -> Resource.Failure(errorMessage = it.errorMessage)
        }
    }
}