package com.example.desafio1.descuentos

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import com.example.desafio1.R
import java.util.Locale

class Function2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function2)

        val etSalary: EditText = findViewById(R.id.etSalary)
        val btnCalculate: Button = findViewById(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            val salaryString = etSalary.text.toString()
            if (salaryString.isNotEmpty()) {
                val salary = salaryString.toDoubleOrNull()
                if (salary != null && salary >= 0) {
                    val descuentos = Descuentos(salary)
                    mostrarResultados(descuentos)
                } else {
                    mostrarError("Por favor, ingrese un salario válido y no negativo.")
                }
            } else {
                mostrarError("El campo de salario no puede estar vacío.")
            }
        }
    }

    private fun mostrarResultados(descuentos: Descuentos) {
        val message = """
            Salario Base: ${formatDecimal(descuentos.salarioBase)}
            AFP (7.25%): ${formatDecimal(descuentos.calcularAFP())}
            ISSS (3%): ${formatDecimal(descuentos.calcularISSS())}
            Renta: ${formatDecimal(descuentos.calcularRenta())}
            Salario Neto: ${formatDecimal(descuentos.calcularSalarioNeto())}
        """.trimIndent()

        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_general, null)
        val tvTitle: TextView = dialogView.findViewById(R.id.tvTitle)
        val tvMessage: TextView = dialogView.findViewById(R.id.tvMessage)
        val btnOk: Button = dialogView.findViewById(R.id.btnOk)

        tvTitle.text = "Detalle de Descuentos"
        tvMessage.text = message

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

    private fun formatDecimal(value: Double): String {
        return String.format(Locale.US, "%.2f", value)
    }
}
