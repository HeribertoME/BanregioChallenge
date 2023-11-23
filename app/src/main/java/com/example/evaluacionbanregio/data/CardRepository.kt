package com.example.evaluacionbanregio.data

import com.example.evaluacionbanregio.ui.CardResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repositorio de la data de tarjeta
 *
 * @param cardDataSource Data source de la tarjeta
 */
@ActivityRetainedScoped
class CardRepository @Inject constructor(
    private val cardDataSource: CardDataSource
) : BaseApiResponse() {

    /**
     * Metodo para obtener la información de la tarjeta
     *
     * @return Flow<NetworkResult<CardResponse>>
     */
    suspend fun getCardInfo(): Flow<NetworkResult<CardResponse>> {
        return flow {
            emit(safeApiCall { cardDataSource.getCardInfo() })
        }.flowOn(Dispatchers.IO)
    }

}
