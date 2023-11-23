package com.example.evaluacionbanregio.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.evaluacionbanregio.data.NetworkResult
import com.example.evaluacionbanregio.databinding.ActivityMainBinding
import com.example.evaluacionbanregio.utils.formatAmount
import com.example.evaluacionbanregio.utils.toFormatCard
import com.example.evaluacionbanregio.utils.toFormatName
import com.example.evaluacionbanregio.utils.toMovementUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovementsAdapter

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            adapter = MovementsAdapter()
            rvMain.adapter = adapter

            viewModel.responseCard.observe(this@MainActivity) {
                when(it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Error -> {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
                    }
                    is NetworkResult.Success -> {
                        etCardFistNumbers.setText(it.data?.NumeroTarjeta?.toFormatCard(0))
                        etCardFistNumbers.keyListener = null

                        etCardSecondNumbers.setText(it.data?.NumeroTarjeta?.toFormatCard(1))
                        etCardSecondNumbers.keyListener = null

                        etCardThirdNumbers.setText(it.data?.NumeroTarjeta?.toFormatCard(2))
                        etCardThirdNumbers.keyListener = null

                        etCardFourthNumbers.setText(it.data?.NumeroTarjeta?.toFormatCard(3))
                        etCardFourthNumbers.keyListener = null
                        etOwnerCardValue.setText(it.data?.TitularTarjeta?.toFormatName() ?: "")
                        etOwnerCardValue.keyListener = null
                        etExpirationDateValue.setText(it.data?.FechaExp ?: "")
                        etExpirationDateValue.keyListener = null
                    }
                }
            }

            viewModel.responseMovements.observe(this@MainActivity) { networkResult ->
                when(networkResult) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Error -> {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
                    }
                    is NetworkResult.Success -> {
                        val result = networkResult.data
                        adapter.submitList(result?.toMovementUI())

                        // Calcular el monto total de las transacciones.
                        val total = result?.sumOf { it.Monto?.toDouble() ?: 0.0 }
                        Toast.makeText(this@MainActivity, "Total: ${total?.formatAmount()}", Toast.LENGTH_LONG).show() // TODO : No se especifica donde mostrar el monto total de las transacciones
                    }
                }
            }

            tvCVVValue.setOnClickListener {
                viewModel.generateNewCVV()
            }

            // Observa los cambios en el LiveData del CVV
            viewModel.cvv.observe(this@MainActivity) { nuevoCVV ->
                tvCVVValue.text = nuevoCVV
            }

            // Observa los cambios en el LiveData del temporizador
            viewModel.timerText.observe(this@MainActivity) { time ->
                if (time.equals("00:00")) {
                    tvTimer.visibility = View.GONE
                } else {
                    tvTimer.visibility = View.VISIBLE
                    tvTimer.text = time
                }
            }

        }
    }
}