package www.udb.edu.sv.promediar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etNota1: EditText
    private lateinit var etNota2: EditText
    private lateinit var etNota3: EditText
    private lateinit var etNota4: EditText
    private lateinit var etNota5: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etNota1 = findViewById(R.id.etNota1)
        etNota2 = findViewById(R.id.etNota2)
        etNota3 = findViewById(R.id.etNota3)
        etNota4 = findViewById(R.id.etNota4)
        etNota5 = findViewById(R.id.etNota5)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)

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

        if (nota1 != null && nota2 != null && nota3 != null && nota4 != null && nota5 != null) {
            if (validarNotas(nota1, nota2, nota3, nota4, nota5)) {
                val promedio = calcularNotaFinal(nota1, nota2, nota3, nota4, nota5)
                val resultado = if (promedio >= 6) "Aprobó" else "Reprobó"
                tvResultado.text = "Promedio de $nombre: ${round(promedio * 100) / 100} - $resultado"

                etNombre.text.clear()
                etNota1.text.clear()
                etNota2.text.clear()
                etNota3.text.clear()
                etNota4.text.clear()
                etNota5.text.clear()
            } else {
                tvResultado.text = "Las notas deben estar entre 0 y 10."
            }
        } else {
            tvResultado.text = "Por favor, ingrese todas las notas."
        }
    }

    private fun validarNotas(nota1: Double, nota2: Double, nota3: Double, nota4: Double, nota5: Double): Boolean {
        return nota1 in 0.0..10.0 && nota2 in 0.0..10.0 && nota3 in 0.0..10.0 && nota4 in 0.0..10.0 && nota5 in 0.0..10.0
    }

    private fun calcularNotaFinal(nota1: Double, nota2: Double, nota3: Double, nota4: Double, nota5: Double): Double {
        return (nota1 * 0.15 + nota2 * 0.15 + nota3 * 0.20 + nota4 * 0.25 + nota5 * 0.25)
    }
}