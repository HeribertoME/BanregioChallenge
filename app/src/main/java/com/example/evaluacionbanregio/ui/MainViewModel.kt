package com.example.evaluacionbanregio.ui

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evaluacionbanregio.data.MovementsResponse
import com.example.evaluacionbanregio.data.NetworkResult
import com.example.evaluacionbanregio.domain.GetCardInfo
import com.example.evaluacionbanregio.domain.GetMovements
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

/**
 * MainViewModel para la pantalla main
 *
 * @param getCardInfo Metodo para obtener la información de la tarjeta
 * @param getMovements Metodo para obtener los movimientos
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCardInfo: GetCardInfo,
    private val getMovements: GetMovements,
) : ViewModel() {

    private var _responseCard = MutableLiveData<NetworkResult<CardResponse>>()
    val responseCard: LiveData<NetworkResult<CardResponse>> = _responseCard

    private var _responseMovements = MutableLiveData<NetworkResult<ArrayList<MovementsResponse>>>()
    val responseMovements: LiveData<NetworkResult<ArrayList<MovementsResponse>>> = _responseMovements

    private val _cvv = MutableLiveData<String>()
    val cvv: LiveData<String> = _cvv

    private val _timerText = MutableLiveData<String>()
    val timerText: LiveData<String> = _timerText

    private var countDownTimer: CountDownTimer? = null
    private val cvvTimerDuration: Long = 5 * 60 * 1000 // 5 minutos en milisegundos

    init {
        fetchData()
        _cvv.value = "Generar CVV"
        _timerText.value = "00:00"
    }

    /**
     * Metodo que hace la consulta a los servicios para obtener la informacion
     */
    private fun fetchData() {
        viewModelScope.launch {
            getCardInfo().collect {
                _responseCard.value = it
            }
            getMovements().collect {
                _responseMovements.value = it
            }
        }
    }

    /**
     * Metodo para generar un cvv nuevo
     */
    fun generateNewCVV() {
        // Genera un CVV aleatorio de tres cifras
        _cvv.value = (100..999).random().toString()

        // Reinicia el temporizador
        restartTimer()
    }

    /**
     * Metodo para iniciar el temporizador
     * Hace uso de la clase CountDownTimer
     */
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(cvvTimerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Actualiza el temporizador en el formato MM:SS
                val minutos = millisUntilFinished / 60000
                val segundos = (millisUntilFinished % 60000) / 1000
                val tiempoRestante = String.format(Locale.getDefault(), "%02d:%02d", minutos, segundos)
                _timerText.value = tiempoRestante
            }

            override fun onFinish() {
                // Cuando el temporizador llega a cero, cambia el texto del temporizador
                _timerText.value = "00:00"
                _cvv.value = "Generar CVV"
            }
        }

        // Inicia el temporizador
        countDownTimer?.start()
    }

    /**
     * Metodo para reiniciar el temporizador
     */
    private fun restartTimer() {
        // Cancela el temporizador actual y inicia uno nuevo
        countDownTimer?.cancel()
        startTimer()
    }

}
