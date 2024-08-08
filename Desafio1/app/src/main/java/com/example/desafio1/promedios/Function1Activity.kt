package com.example.desafio1.promedios

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import com.example.desafio1.R

class Function1Activity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etNota1: EditText
    private lateinit var etNota2: EditText
    private lateinit var etNota3: EditText
    private lateinit var etNota4: EditText
    private lateinit var etNota5: EditText
    private lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function1)

        etNombre = findViewById(R.id.etNombre)
        etNota1 = findViewById(R.id.etNota1)
        etNota2 = findViewById(R.id.etNota2)
        etNota3 = findViewById(R.id.etNota3)
        etNota4 = findViewById(R.id.etNota4)
        etNota5 = findViewById(R.id.etNota5)
        btnCalcular = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            calcularPromedio()
        }
    }

    private fun calcularPromedio() {
        val nombre = etNombre.text.toString()
        val nota1 = etNota1.text.toString().toDoubleOrNull()
        val nota2 = etNota2.text.toString().toDoubleOrNull()
        val nota3 = etNota3.text.toString().toDoubleOrNull()
        val nota4 = etNota4.text.toString().toDoubleOrNull()
        val nota5 = etNota5.text.toString().toDoubleOrNull()

        if (nombre.isNotBlank() && nota1 != null && nota2 != null && nota3 != null && nota4 != null && nota5 != null) {
            try {
                val promedio = Promedios(nombre, nota1, nota2, nota3, nota4, nota5)
                mostrarResultado("Promedio: ${promedio.obtenerResultado()}")
            } catch (e: IllegalArgumentException) {
                mostrarError(e.message ?: "Error desconocido")
            }
        } else {
            mostrarError("Por favor, ingrese todas las notas y el nombre.")
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
