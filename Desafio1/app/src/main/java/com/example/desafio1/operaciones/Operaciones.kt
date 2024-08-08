package com.example.desafio1.operaciones

class Operaciones(private val operando1: Double, private val operando2: Double) {

    fun sumar(): Double = operando1 + operando2

    fun restar(): Double = operando1 - operando2

    fun multiplicar(): Double = operando1 * operando2

    fun dividir(): Double {
        if (operando2 == 0.0) throw ArithmeticException("No se puede dividir por cero")
        return operando1 / operando2
    }

}