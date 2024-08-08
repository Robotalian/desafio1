package com.example.desafio1.operaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import com.example.desafio1.R

class Function3Activity : AppCompatActivity() {

    private lateinit var etOperando1: EditText
    private lateinit var etOperando2: EditText
    private lateinit var spinnerOperacion: Spinner
    private lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function3)

        etOperando1 = findViewById(R.id.etOperando1)
        etOperando2 = findViewById(R.id.etOperando2)
        spinnerOperacion = findViewById(R.id.spinnerOperacion)
        btnCalcular = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            realizarOperacion()
        }
    }

    private fun realizarOperacion() {
        val operando1String = etOperando1.text.toString()
        val operando2String = etOperando2.text.toString()
        val operando1 = operando1String.toDoubleOrNull()
        val operando2 = operando2String.toDoubleOrNull()

        if (operando1 != null && operando2 != null) {
            val operacion = spinnerOperacion.selectedItem.toString()
            try {
                val calculadora = Operaciones(operando1, operando2)
                val resultado = when (operacion) {
                    "Suma" -> calculadora.sumar()
                    "Resta" -> calculadora.restar()
                    "Multiplicación" -> calculadora.multiplicar()
                    "División" -> calculadora.dividir()
                    else -> throw IllegalArgumentException("Operación no válida")
                }
                mostrarResultado("El resultado es: $resultado")
            } catch (e: ArithmeticException) {
                mostrarError(e.message ?: "Error en la operación")
            } catch (e: IllegalArgumentException) {
                mostrarError(e.message ?: "Error en la operación")
            }
        } else {
            mostrarError("Por favor, ingrese ambos operandos.")
        }
    }

    private fun mostrarResultado(mensaje: String) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_general, null)
        val tvTitle: TextView = dialogView.findViewById(R.id.tvTitle)
        val tvMessage: TextView = dialogView.findViewById(R.id.tvMessage)
        val btnOk: Button = dialogView.findViewById(R.id.btnOk)

        tvTitle.text = "Resultado"
        tvMessage.text = mensaje

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        btnOk.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun mostrarError(mensaje: String) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_general, null)
        val tvTitle: TextView = dialogView.findViewById(R.id.tvTitle)
        val tvMessage: TextView = dialogView.findViewById(R.id.tvMessage)
        val btnOk: Button = dialogView.findViewById(R.id.btnOk)

        tvTitle.text = "Error"
        tvMessage.text = mensaje

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        btnOk.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
