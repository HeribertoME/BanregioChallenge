package com.example.evaluacionbanregio.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repositorio de la data de movimientos
 *
 * @param movementsDataSource Data source de los movimientos
 */
@ActivityRetainedScoped
class MovementsRepository @Inject constructor(
    private val movementsDataSource: MovementDataSource
) : BaseApiResponse() {

    suspend fun getMovements(): Flow<NetworkResult<ArrayList<MovementsResponse>>> {
        return flow {
            emit(safeApiCall { movementsDataSource.getMovements() })
        }.flowOn(Dispatchers.IO)
    }

}
