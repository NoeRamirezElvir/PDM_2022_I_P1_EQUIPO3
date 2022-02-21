package hn.edu.ujcv.pdm_2022_i_p1_equipo3

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import clases.Libro
import hn.edu.ujcv.pdm_2022_i_p1_equipo3.databinding.ActivityIngresarLibrosBinding
import kotlinx.android.synthetic.main.activity_ingresar_libros.*


class IngresarLibrosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIngresarLibrosBinding
    var listaLibros:ArrayList<Libro> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresarLibrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inicializar()

        binding.btnGuardar.setOnClickListener {
            if (!validarBoton()) {
                Toast.makeText(this, mensaje(), Toast.LENGTH_SHORT).show()
            } else {
                var libro: Libro = Libro()
                registrar(libro)
                mostrarDialog(libro)

                //botones
                binding.btnRegresar.setOnClickListener { regresar() }
                binding.btnMostrar.setOnClickListener { mostrar() }
            }
        }
    }

    private fun inicializar() {
        fecha()
        validar()

        val intent = intent
        if (intent.getSerializableExtra("libros") != null) {
            listaLibros = intent.getSerializableExtra("libros") as ArrayList<Libro>
        }
    }

    private fun registrar(libro: Libro) {
        libro.isbn             = txtNumeroLibro.text.toString().toLong()
        libro.nombre           = txtNombreLibro.text.toString()
        libro.autor            = txtAutor.text.toString()
        libro.fechaPublicacion = txtFecha.text.toString()
        libro.editorial        = txtEditorial.text.toString()
    }

    private fun mostrarDialog(libro: Libro) {
        var mensaje = "ISBN: " + txtNumeroLibro.text
            mensaje += "\nTitulo: " + txtNombreLibro.text
            mensaje += "\nAutor: " + txtAutor.text
            mensaje += "\nPublicado en: " + txtFecha.text
            mensaje += "\nEditorial: " + txtEditorial.text
        val dialog = AlertDialog.Builder(this)
            .setTitle("Desea registrar este libro?")
            .setMessage(mensaje)
            .setPositiveButton("Si") {_,_ ->
                listaLibros.add(libro)
                Toast.makeText(this, "Guardado Exitosamente", Toast.LENGTH_SHORT).show()
                limpiar()
            }
            .setNegativeButton("No"){_,_ ->
                Toast.makeText(this, "No se guardo el registro", Toast.LENGTH_SHORT).show()
            }.create()
        dialog.show()
    }

    private fun regresar() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("libros", listaLibros)
        startActivity(intent)
    }

    private fun mostrar() {
        val intent = Intent(this, MostrarLibrosActivity::class.java)
        intent.putExtra("libros", listaLibros)
        startActivity(intent)
    }

    @SuppressLint("NewApi")
    private fun fecha() {
        val calendar = Calendar.getInstance()
        val año = calendar.get(Calendar.YEAR)
        val mes = calendar.get(Calendar.MONTH)
        val dia = calendar.get(Calendar.DAY_OF_MONTH)
        var month:Int

        txtFecha.setOnClickListener {
            val date_dialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, mYear, mMes, mDia ->
                month = mMes + 1
                txtFecha.setText("" + mDia + "/" + month + "/" + mYear)
            }, año, mes, dia)
            date_dialog.show()
        }
    }

    private fun validar() {
        txtNumeroLibro.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrEmpty()) {
                txtNumeroLibro.error = "El ISBN no debe estar vacio"
            }
            if (text!!.length < 13) {
                txtNumeroLibro.error = "El ISBN es muy corto"
            }
            if (text!!.length > 13) {
                txtNumeroLibro.error = "El ISBN es demasiado largo"
            }
        }

        txtNombreLibro.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrEmpty()) {
                txtNombreLibro.error = "El Titulo del Libro esta vacio"
            }
            if (text!!.length < 3) {
                txtNombreLibro.error = "El Titulo del Libro  es muy corto"
            }
            if (text!!.length > 40) {
                txtNombreLibro.error = "El Titulo del Libro  es demasiado largo"
            }
        }

        txtAutor.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrEmpty()) {
                txtAutor.error = "El Nombre de Autor no debe estar vacio"
            }
            if (text!!.length < 5) {
                txtAutor.error = "Ingrese mas de cinco caracteres"
            }
            if (text!!.length > 40) {
                txtAutor.error = "El Nombre de Autor es demasiado largo"
            }
        }

        txtFecha.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrEmpty()) {
                txtFecha.error = "El Fecha de Publicacion no debe estar vacio"
            }
        }

        txtEditorial.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrEmpty()) {
                txtEditorial.error = "La Editorial esta vacia"
            }
            if (text!!.length < 5) {
                txtEditorial.error = "Ingrese mas de cinco caracteres"
            }
            if (text!!.length > 40) {
                txtEditorial.error = "La Editorial es muy larga"
            }
        }
    }

    private fun limpiar() {
        txtNumeroLibro.text = null
        txtNombreLibro.text = null
        txtAutor.text = null
        txtFecha.text = null
        txtEditorial.text = null

        //mensajes de error
        txtNumeroLibro.error = null
        txtNombreLibro.error = null
        txtAutor.error = null
        txtFecha.error = null
        txtEditorial.error = null
    }

    private fun validarBoton():Boolean {
        when {
            txtNumeroLibro.text.isNullOrEmpty() -> return false
            txtNumeroLibro.text!!.length < 13 -> return false
            txtNumeroLibro.text!!.length > 13 -> return false
            txtNombreLibro.text.isNullOrEmpty() -> return false
            txtNombreLibro.text!!.length < 3 -> return false
            txtNombreLibro.text!!.length > 40 -> return false
            txtAutor.text.isNullOrEmpty() -> return false
            txtAutor.text!!.length < 5 -> return false
            txtAutor.text!!.length > 40 -> return false
            txtFecha.text.isNullOrEmpty() -> return false
            txtEditorial.text.isNullOrEmpty() -> return false
            txtEditorial.text!!.length < 5 -> return false
            txtEditorial.text!!.length > 40 -> return false
            else -> return true
        }
    }

    private fun mensaje():String {
        val m1:String = "No deje campos vacios"
        val m2:String = "Corrija datos"
        when {
            txtNumeroLibro.text.isNullOrEmpty() -> return m1
            txtNombreLibro.text.isNullOrEmpty() -> return m1
            txtAutor.text.isNullOrEmpty() -> return m1
            txtFecha.text.isNullOrEmpty() -> return m1
            txtEditorial.text.isNullOrEmpty() -> return m1
            else -> return m2
        }
    }
}