package clases

import android.util.Patterns
import java.io.Serializable
import kotlin.Exception
import kotlin.IllegalArgumentException

class Alumno:Comparable<Alumno>,Serializable{
    //numero cuenta,nombre,carrera,fecha ingreso,correo
    var numeroCuenta: Long? = 0L
    var nombre: String? = null
    var carrera: String? = null
    var fechaIngreso:String? = null
    var correo: String? = null
    var genero: String? = null

    constructor()
    constructor(
        pNumeroCuenta:Long?,pNombre:String?,pCarrera:String?,pFechaIngreso:String?,pCorreo:String?
    ){
        try{
            if (pNumeroCuenta == null) {
                throw IllegalArgumentException("El número de cuenta está en blanco.")
            }
            if (pNumeroCuenta <= 0) {
                throw IllegalArgumentException("El número de cuenta debe mayor a 0.")
            }
            if (pNumeroCuenta.toString().length < 10) {
                throw IllegalArgumentException("El número de cuenta es muy corto.")
            }
            if (pNumeroCuenta.toString().length > 10) {
                throw IllegalArgumentException("El número de cuenta es muy largo.")
            }
            if (pNombre.isNullOrEmpty()) {
                throw IllegalArgumentException("El nombre está en blanco.")
            }
            if (pNombre.length < 3) {
                throw IllegalArgumentException("El nombre es muy corto.")
            }
            if (pNombre.length > 40) {
                throw IllegalArgumentException("El nombre es muy largo.")
            }
            if (pCarrera.isNullOrEmpty()) {
                throw IllegalArgumentException("La carrera está en blanco.")
            }
            if (pCarrera.length < 3) {
                throw IllegalArgumentException("La carrera es muy corta.")
            }
            if (pCarrera.length > 50) {
                throw IllegalArgumentException("La carrera es muy larga.")
            }
            if (pFechaIngreso.isNullOrEmpty()) {
                throw IllegalArgumentException("La fecha de ingreso está en blanco.")
            }
            if (pFechaIngreso.length < 5) {
                throw IllegalArgumentException("La fecha de ingreso es muy corta.")
            }
            if (pFechaIngreso.length > 50) {
                throw IllegalArgumentException("La fecha de ingreso es muy larga.")
            }
            if (pCorreo.isNullOrEmpty()) {
                throw IllegalArgumentException("El correo está en blanco.")
            }
            if (pCorreo.length < 5) {
                throw IllegalArgumentException("El correo es muy corto.")
            }
            if (pCorreo.length > 50) {
                throw IllegalArgumentException("El correo es muy largo.")
            }
            numeroCuenta = pNumeroCuenta
            nombre       = pNombre
            carrera      = pCarrera
            fechaIngreso = pFechaIngreso
            correo       = pCorreo
        }catch(e: Exception){
            throw Exception(e.message)
        }
    }

    override fun compareTo(other: Alumno): Int {
        return (this.numeroCuenta!! - other.numeroCuenta!!).toInt()
    }
    inner class ValidacionesAlumnos{
        fun validarCuenta(value:Long?){
            if (value == null) {
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
        }
        fun validarNombre(value:String?){
            if (value.isNullOrEmpty()) {
                throw IllegalArgumentException("El nombre está en blanco.")
            }
            if (value.length < 3) {
                throw IllegalArgumentException("El nombre es muy corto.")
            }
            if (value.length > 40) {
                throw IllegalArgumentException("El nombre es muy largo.")
            }
        }
        fun validarCarrera(value:String?){
            if (value.isNullOrEmpty()) {
                throw IllegalArgumentException("La carrera está en blanco.")
            }
            if (value.length < 3) {
                throw IllegalArgumentException("La carrera es muy corta.")
            }
            if (value.length > 50) {
                throw IllegalArgumentException("La carrera es muy larga.")
            }
        }
        fun validarFecha(value:String?){
            if (value.isNullOrEmpty()) {
                throw IllegalArgumentException("La fecha de ingreso está en blanco.")
            }
            if (value.length < 5) {
                throw IllegalArgumentException("La fecha de ingreso es muy corta.")
            }
            if (value.length > 50) {
                throw IllegalArgumentException("La fecha de ingreso es muy larga.")
            }
        }
        fun validarCorreo(value:String?){
            if (value.isNullOrEmpty()) {
                throw IllegalArgumentException("El correo está en blanco.")
            }
            if (value.length < 5) {
                throw IllegalArgumentException("El correo es muy corto.")
            }
            if (value.length > 50) {
                throw IllegalArgumentException("El correo es muy largo.")
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
                throw IllegalArgumentException("Dirección de correo invalida.")
            }
        }
        fun validarGenero(value:String?){
            if (value.isNullOrEmpty()) {
                throw IllegalArgumentException("El genero está en blanco.")
            }
            if (value.length < 5) {
                throw IllegalArgumentException("El genero es muy corto.")
            }
            if (value.length > 50) {
                throw IllegalArgumentException("El genero es muy largo.")
            }
        }
    }

}