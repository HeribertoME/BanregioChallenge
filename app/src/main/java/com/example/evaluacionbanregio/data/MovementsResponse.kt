package com.example.evaluacionbanregio.data

import com.google.gson.annotations.SerializedName


/**
 * Data class modelo de respuesta del servicio tarjeta de credito
 */
data class MovementsResponse (
    @SerializedName("pkMovimientosID" ) var pkMovimientosID : String? = null,
    @SerializedName("Descripcion"     ) var Descripcion     : String? = null,
    @SerializedName("Fecha"           ) var Fecha           : String? = null,
    @SerializedName("Monto"           ) var Monto           : String? = null
)
