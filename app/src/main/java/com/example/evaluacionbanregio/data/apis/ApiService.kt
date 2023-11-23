package com.example.evaluacionbanregio.data.apis

import com.example.evaluacionbanregio.data.MovementsResponse
import com.example.evaluacionbanregio.ui.CardResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface that provides methods to obtain the data source
 */
interface ApiService {

    /**
     * Consume el microservicio para traer la información de la tarjeta
     */
    @GET("rest/tarjetacredito.php/1")
    suspend fun getCardInfo(): Response<CardResponse>

    /**
     * Consumir el microservicio para traer los movimientos de la tarjeta
     */
    @GET("rest/tarjetacredito-movimientos.php/3")
    suspend fun getMovements(): Response<ArrayList<MovementsResponse>>
}
