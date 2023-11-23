package com.example.evaluacionbanregio.data

import com.example.evaluacionbanregio.data.apis.ApiService
import retrofit2.Response
import javax.inject.Inject

/**
 * Interfaz que proporciona los metodos para obtener la data source de movimientos
 */
interface MovementDataSource {

    /**
     * Obtiene movimientos
     */
    suspend fun getMovements(): Response<ArrayList<MovementsResponse>>

}

/**
 * Clase que implementa la interface [MovementDataSource]
 * obtiene los datos de los movimientos remotos
 *
 * @param service ApiService para obtener los datos desde el servidor
 */
class MovementsRemoteSource @Inject constructor(
    private val service: ApiService,
) : MovementDataSource {
    override suspend fun getMovements() = service.getMovements()

}
