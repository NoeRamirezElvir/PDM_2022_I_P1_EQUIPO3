package clases

import java.io.Serializable
import java.lang.IllegalArgumentException

class Libro:Comparable<Libro>, Serializable{
    var isbn: Long? = 0L
    var nombre: String? = null
    var autor:String? = null
    var fechaPublicacion:String? = null
    var editorial:String? = null

    constructor()
    constructor(pNumeroLibro:Long?, pNombre:String?,
                pAutor:String?, pFecha:String?, pEditorial:String?) {
        try {
            if (pNumeroLibro == null) {
                throw IllegalArgumentException("El ISBN no debe estar vacio.")
            }
            if (pNumeroLibro!! < 0) {
                throw IllegalArgumentException("El ISBN no debe ser menor a 0.")
            }
            if (pNumeroLibro!!.toString().length < 13) {
                throw IllegalArgumentException("El ISBN es muy corto")
            }
            if (pNumeroLibro!!.toString().length > 13) {
                throw IllegalArgumentException("El ISBN es demasiado largo")
            }
            if (pNombre.isNullOrEmpty()) {
                throw IllegalArgumentException("El Titulo del Libro esta vacio ")
            }
            if (pNombre!!.length < 3) {
                throw IllegalArgumentException("El Titulo del Libro es muy corto")
            }
            if (pNombre!!.length > 40) {
                throw IllegalArgumentException("El Titulo del Libro es demasiado largo")
            }
            if (pAutor.isNullOrEmpty()) {
                throw IllegalArgumentException("El Nombre de Autor no debe estar vacio")
            }
            if (pAutor!!.length < 5) {
                throw IllegalArgumentException("Debe ingresar mas de cinco caracteres en " +
                        "el nombre de autor")
            }
            if (pAutor!!.length > 40) {
                throw IllegalArgumentException("El Nombre de Autor es demasiado largo")
            }
            if (pFecha.isNullOrEmpty()) {
                throw IllegalArgumentException("La Fecha de Publicacion no debe estar vacio")
            }
            if (pFecha!!.length < 5) {
                throw IllegalArgumentException("La Fecha de Publicacion es muy corta")
            }
            if (pFecha!!.length > 11) {
                throw IllegalArgumentException("La Fecha de Publicacion es demasiado larga")
            }
            if (pEditorial.isNullOrEmpty()) {
                throw IllegalArgumentException("La Editorial esta vacia")
            }
            if (pEditorial!!.length < 5) {
                throw IllegalArgumentException("Ingrese mas de cinco caracteres en la Editorial")
            }
            if (pEditorial!!.length > 40) {
                throw IllegalArgumentException("La Editorial es muy larga")
            }

            isbn             = pNumeroLibro
            nombre           = pNombre
            autor            = pAutor
            fechaPublicacion = pFecha
            editorial        = pEditorial

        } catch (e:Exception) {
            throw Exception(e.message)
        }
    }

    override fun compareTo(other: Libro): Int {
        return (this.isbn!! - other.isbn!!).toInt()
    }

}