package com.example.hw1_guessinggame

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class NumberGuessActivity : AppCompatActivity() {
    private lateinit var nombreEditText: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var numeroEditText: EditText
    private lateinit var messageTextView: TextView
    private lateinit var adivinarButton: View
    private lateinit var reiniciarButton: View
    private var numeroAdivinar: Int = 0
    private var intentos: Int = 0
    private var nombreJugador: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guess)

        nombreEditText = findViewById(R.id.nombreEditText)
        progressBar = findViewById(R.id.progressBar)
        numeroEditText = findViewById(R.id.numeroEditText)
        messageTextView = findViewById(R.id.messageTextView)
        adivinarButton = findViewById(R.id.adivinarButton)
        reiniciarButton = findViewById(R.id.reiniciarButton)

        generarNumeroAleatorio()
    }

    fun adivinarNumero(view: View) {
        val numeroIngresado = numeroEditText.text.toString().toIntOrNull()

        if (numeroIngresado != null) {
            intentos++
            progressBar.progress = intentos

            if (numeroIngresado == numeroAdivinar) {
                mostrarMensaje("¡Ganaste, $nombreJugador! Adivinaste en $intentos intentos.")
                adivinarButton.isEnabled = false
                reiniciarButton.isEnabled = true
            } else {
                val mensaje = if (numeroIngresado < numeroAdivinar) {
                    "El número es mayor"
                } else {
                    "El número es menor"
                }
                messageTextView.text = mensaje
            }
        } else {
            messageTextView.text = "Ingresa un número válido"
        }
    }

    fun reiniciarJuego(view: View) {
        nombreJugador = nombreEditText.text.toString()
        nombreEditText.text.clear()
        progressBar.progress = 0
        numeroEditText.text.clear()
        messageTextView.text = "Mensaje"
        intentos = 0
        generarNumeroAleatorio()
        adivinarButton.isEnabled = true
        reiniciarButton.isEnabled = true
    }

    private fun generarNumeroAleatorio() {
        numeroAdivinar = Random.nextInt(101)
    }

    private fun mostrarMensaje(mensaje: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .create()

        dialog.show()
    }
}
