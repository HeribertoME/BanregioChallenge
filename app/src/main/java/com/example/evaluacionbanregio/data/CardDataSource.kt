package com.example.evaluacionbanregio.data

import com.example.evaluacionbanregio.data.apis.ApiService
import com.example.evaluacionbanregio.ui.CardResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Interface that provides methods to obtain the data source of card
 */
interface CardDataSource {

    /**
     * Obtiene los datos de la tarjeta
     */
    suspend fun getCardInfo(): Response<CardResponse>

}

/**
 * Clase que implementa la interface [CardDataSource]
 * Obtene los datos de la tarjeta remotamente
 *
 * @param service ApiService para obtener los datos desde el servidor
 */
class CardRemoteSource @Inject constructor(
    private val service: ApiService,
) : CardDataSource {

    override suspend fun getCardInfo() = service.getCardInfo()

}