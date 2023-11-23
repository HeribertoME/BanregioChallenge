package com.example.evaluacionbanregio.data

/**
 * Clase sealed para manejar los resultados
 */
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    /**
     * Status exitoso
     */
    class Success<T>(data: T) : NetworkResult<T>(data)

    /**
     * Status error
     */
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)

    /**
     * Status de carga
     */
    class Loading<T> : NetworkResult<T>()

}