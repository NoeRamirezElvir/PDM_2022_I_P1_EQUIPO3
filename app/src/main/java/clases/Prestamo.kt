package clases

import kotlinx.android.synthetic.main.activity_ingresar_libros.*
import java.io.Serializable

import kotlin.Exception

class Prestamo:Comparable<Prestamo>,Serializable {
    var numeroPrestamo:  Long   = 0L
    set(value) {
        if (value <= 0){
            throw IllegalArgumentException("Número de préstamo no debe ser menor o igual a cero")
        }
        if (value.toString().length > 10){
            throw IllegalArgumentException("Número de préstamo no debe ser mayor a 10")
        }
        field = value
    }
    var fechaPrestamo :  String? = null
    set(value) {
        if (value.isNullOrEmpty()){
            throw IllegalArgumentException("Fecha del Préstamo Vacio")
        }
        field = value
    }
    var numeroCuenta  :  Long    = 0L
    set(value) {
        if (value == null) {
            throw Exception("El número de cuenta está en blanco.")
        }
        if (value.toString().isNullOrEmpty()) {
            throw Exception("El número de cuenta está en blanco.")
        }
        if (value <= 0) {
            throw Exception("El número de cuenta debe mayor a 0.")
        }
        if (value.toString().length < 10) {
            throw Exception("El número de cuenta es muy corto.")
        }
        if (value.toString().length > 10) {
            throw Exception("El número de cuenta es muy largo.")
        }
        field = value
    }
    var numeroLibro   :  Long    = 0L
    set(value) {
        if (value == null) {
            throw java.lang.IllegalArgumentException("El ISBN no debe estar vacio.")
        }
        if (value!! < 0) {
            throw java.lang.IllegalArgumentException("El ISBN no debe ser menor a 0.")
        }
        if (value!!.toString().length < 13) {
            throw java.lang.IllegalArgumentException("El ISBN es muy corto")
        }
        if (value!!.toString().length > 13) {
            throw java.lang.IllegalArgumentException("El ISBN es demasiado largo")
        }
        field = value
    }
    var fechaDev      :  String? = null
    set(value) {
        if (value.isNullOrEmpty()){
            throw IllegalArgumentException("Fecha de devolucion esta Vacio")
        }
        field = value
    }
    constructor()
    constructor(pNumeroPrestamo: Long ,pFechaPrestamo: String? ,pNumeroCuenta: Long
                , pNumeroLibro :Long, pFechaDev:String?)
    {
        try {
            if (pNumeroPrestamo <= 0){
                throw IllegalArgumentException("Numero de prestamos no debe ser menor o igual a cero")
            }
            if (pFechaPrestamo.isNullOrEmpty()){
                throw IllegalArgumentException("Falta registro de Fecha!")
            }
            if (pNumeroCuenta <= 0){
                throw IllegalArgumentException("Numero de Cuenta no debe ser menor o igual a cero")
            }
            if (pNumeroLibro <= 0){
                throw IllegalArgumentException("Numero de Libro no debe ser menor o igual a cero")
            }
            if (pFechaDev.isNullOrEmpty()){
                throw IllegalArgumentException("Falta registro de Fecha!")
            }
        }catch (e : Exception){
            throw Exception(e.message)
        }
    }

    override fun compareTo(other: Prestamo): Int {
        return (this.numeroCuenta!! - other.numeroCuenta!!).toInt()
    }
}