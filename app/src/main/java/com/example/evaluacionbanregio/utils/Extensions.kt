package com.example.evaluacionbanregio.utils

import com.example.evaluacionbanregio.data.MovementsResponse
import com.example.evaluacionbanregio.ui.Movement
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Metodo de utilidad para parsear data de network a data UI
 *
 * @return listado de objetos Movement List<Movement>
 */
fun ArrayList<MovementsResponse>.toMovementUI(): List<Movement> {
    val list = mutableListOf<Movement>()
    this.forEach {
        list.add(Movement(
            id = it.pkMovimientosID?.toInt() ?: 0,
            description = it.Descripcion ?: "",
            date = it.Fecha ?: "",
            amount = it.Monto?.toDouble() ?: 0.0
        ))
    }

    return list
}

/**
 * Metodo util para formatear un numero de tarjeta
 *
 * @param position indice del numero de la tarjeta
 *
 * @return digitos de la tarjeta
 */
fun String.toFormatCard(position: Int): String {
    val digits = this.split("-")
    return digits[position]
}

/**
 * Funcion que permite formatear un nombre
 *
 * @return String con la primer palabra en mayuscula y el resto en minuscula
 */
fun String.toFormatName(): String {
    // Convierte la primera letra a mayúscula y el resto a minúsculas
    if (this.isEmpty()) {
        return this
    }

    // Dividir la cadena en palabras
    val palabras = this.split(" ")

    // Convertir cada palabra: la primera letra a mayúscula, el resto a minúsculas
    val palabrasConvertidas = palabras.map { it.substring(0, 1).uppercase() + it.substring(1).lowercase() }

    // Unir las palabras convertidas en una sola cadena
    return palabrasConvertidas.joinToString(" ")
}

/**
 * Metodo para formatear valor del monto en movimientos
 */
fun Double.formatAmount(): String {
    return String.format("-$%.2f", this)
}

/**
 * Metodo para formatear una fecha en formato yyyy-MM-dd a Diciembre 12 2019
 */
fun String.formatDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())

    try {
        val date = inputFormat.parse(this)
        return outputFormat.format(date!!)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this // Devuelve la fecha original en caso de error
}