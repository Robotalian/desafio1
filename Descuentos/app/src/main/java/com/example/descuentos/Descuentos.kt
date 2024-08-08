package com.example.descuentos

import java.util.Locale

class Descuentos(val salarioBase: Double) {

    fun calcularAFP(): Double {
        return salarioBase * 0.0725
    }

    fun calcularISSS(): Double {
        return salarioBase * 0.03
    }

    fun calcularRenta(): Double {
        return when {
            salarioBase <= 472.00 -> 0.0
            salarioBase <= 895.24 -> (salarioBase - 472.00) * 0.10 + 17.67
            salarioBase <= 2038.10 -> (salarioBase - 895.24) * 0.20 + 60.00
            else -> (salarioBase - 2038.10) * 0.30 + 288.57
        }
    }

    fun calcularSalarioNeto(): Double {
        val afp = calcularAFP()
        val isss = calcularISSS()
        val renta = calcularRenta()
        return salarioBase - afp - isss - renta
    }

    // Formatear los valores numéricos con 2 decimales
    fun obtenerDetallesDescuentos(): String {
        val afp = calcularAFP()
        val isss = calcularISSS()
        val renta = calcularRenta()
        val neto = calcularSalarioNeto()

        return """
            Salario Base: ${formatDecimal(salarioBase)}
            AFP (7.25%): ${formatDecimal(afp)}
            ISSS (3%): ${formatDecimal(isss)}
            Renta: ${formatDecimal(renta)}
            Salario Neto: ${formatDecimal(neto)}
        """.trimIndent()
    }

    private fun formatDecimal(value: Double): String {
        return String.format(Locale.US, "%.2f", value)
    }
}