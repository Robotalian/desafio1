package com.example.desafio1.promedios

class Promedios(
    private val nombre: String,
    private val nota1: Double,
    private val nota2: Double,
    private val nota3: Double,
    private val nota4: Double,
    private val nota5: Double
) {

    init {
        require(validarNotas()) { "Las notas deben estar entre 0 y 10." }
    }

    private fun validarNotas(): Boolean {
        return nota1 in 0.0..10.0 && nota2 in 0.0..10.0 && nota3 in 0.0..10.0 &&
                nota4 in 0.0..10.0 && nota5 in 0.0..10.0
    }

    fun calcularNotaFinal(): Double {
        return (nota1 * 0.15 + nota2 * 0.15 + nota3 * 0.20 + nota4 * 0.25 + nota5 * 0.25)
    }

    fun obtenerResultado(): String {
        val promedio = calcularNotaFinal()
        val estado = if (promedio >= 6) "Aprobó" else "Reprobó"
        return "Promedio de $nombre: ${"%.2f".format(promedio)} - $estado"
    }
}
