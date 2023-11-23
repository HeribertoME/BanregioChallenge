package com.example.evaluacionbanregio.ui

import com.google.gson.annotations.SerializedName

/**
 * Data class modelo de respuesta del servicio tarjeta de credito
 */
data class CardResponse (
    @SerializedName("pkTarjetaCreditoID" ) var pkTarjetaCreditoID : Int?    = null,
    @SerializedName("Nombre_Banco"       ) var NombreBanco        : String? = null,
    @SerializedName("Numero_Tarjeta"     ) var NumeroTarjeta      : String? = null,
    @SerializedName("Titular_Tarjeta"    ) var TitularTarjeta     : String? = null,
    @SerializedName("Fecha_Exp"          ) var FechaExp           : String? = null,
    @SerializedName("CVV"                ) var CVV                : Int?    = null,
    @SerializedName("Monto"              ) var Monto              : Int?    = null
)