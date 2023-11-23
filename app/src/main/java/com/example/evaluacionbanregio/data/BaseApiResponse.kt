package com.example.evaluacionbanregio.data

import retrofit2.Response

/**
 * Clase base para las llamadas a la API
 */
abstract class BaseApiResponse {

    /**
     * Metodo para manejar las llamadas a la API
     */
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    /**
     * Metodo para manejar el error de las llamadas a la API
     */
    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")

}