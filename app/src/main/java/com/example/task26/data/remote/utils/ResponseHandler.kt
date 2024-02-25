package com.example.task26.data.remote.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ResponseHandler @Inject constructor() {
    fun <T : Any> handleApiCall(apiCall: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Failure("Error Code: ${response.code()}"))
            }
        } catch (e: IOException) {
            emit(Resource.Failure("Network error: ${e.message}"))
        } catch (e: HttpException) {
            emit(Resource.Failure("HTTP error: ${e.message}"))
        } catch (e: Exception) {
            emit(Resource.Failure("Unknown error: ${e.message}"))
        }
    }
}