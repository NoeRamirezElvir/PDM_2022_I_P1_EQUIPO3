package hn.edu.ujcv.pdm_2022_i_p1_equipo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import clases.Alumno
import clases.Libro
import clases.Prestamo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listaAlumnos  :ArrayList<Alumno>?   = ArrayList()
    var listaLibros   :ArrayList<Libro>?    = ArrayList()
    var listaPrestamos:ArrayList<Prestamo>? = ArrayList()
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializar()
        button.setOnClickListener{mostrarRegistroAlumnos()}
        btnMostrarAlumno.setOnClickListener{mostrarListaAlumnos()}
        //
        btnIngresarLibros.setOnClickListener { mostrarRegistroLibros() }
        btnMostrarLibros.setOnClickListener { mostrarListaLibros() }
        //
        btnIngresarP.setOnClickListener { mostrarIngresoP() }
        btnVisualizarP.setOnClickListener { mostrarListaPrestamos() }
    }
    //alumnos
    private fun mostrarRegistroAlumnos(){
        val intent = Intent(this,RegistrarAlumnosActivity::class.java)
        intent.putExtra("lista",listaAlumnos)
        startActivity(intent)
    }

    private fun mostrarListaAlumnos(){
        val intent = Intent(this,MostrarAlumnosActivity::class.java)
        if(listaAlumnos.isNullOrEmpty()){
            val cancelDialog = AlertDialog.Builder(this)
                .setTitle("No hay registro para mostrar")
                .setMessage("Falta de información")
                .setIcon(R.drawable.icono_cancelar)
                .setPositiveButton("Aceptar"){_,_ ->
                }.create()
            cancelDialog.show()
        }else{
            intent.putExtra("lista",listaAlumnos)
            startActivity(intent)
        }
    }
    //
    private fun inicializar(){
        val intent1 = intent
        if(intent1.getSerializableExtra("lista") != null){
            listaAlumnos = intent.getSerializableExtra("lista") as ArrayList<Alumno>
        }
        val intent2 = intent
        if(intent2.getSerializableExtra("libros") != null){
            listaLibros = intent.getSerializableExtra("libros") as ArrayList<Libro>
        }
        val intent3 = intent
        if (intent3.getSerializableExtra("prestamos") != null){
            listaPrestamos = intent.getSerializableExtra("prestamos") as ArrayList<Prestamo>
        }
    }
    //libros
    private fun mostrarRegistroLibros(){
        val intent = Intent(this,IngresarLibrosActivity::class.java)
        intent.putExtra("libros",listaLibros)
        startActivity(intent)
    }

    private fun mostrarListaLibros() {
        val intent = Intent(this,MostrarLibrosActivity::class.java)
    if(listaLibros.isNullOrEmpty()){
            val cancelDialog = AlertDialog.Builder(this)
                .setTitle("No hay registro para mostrar")
                .setMessage("Falta de información")
                .setIcon(R.drawable.icono_cancelar)
                .setPositiveButton("Aceptar"){_,_ ->
                }.create()
            cancelDialog.show()
        }else{
            intent.putExtra("libros",listaLibros)
            startActivity(intent)
        }

    }
    // prestamos
    private fun mostrarIngresoP(){
        val intentp = Intent(this,IngresarPrestamoActivity::class.java)
        intentp.putExtra("prestamos",listaPrestamos)
        startActivity(intentp)
    }
    private fun mostrarListaPrestamos() {
        val intentLP = Intent(this,MostarPrestamosActivity::class.java)
        if (listaPrestamos.isNullOrEmpty()){
            val cancelDialog = AlertDialog.Builder (this)
                .setTitle("No hay registros para Mostrar")
                .setMessage("Falta de Información")
                .setIcon(R.drawable.icono_cancelar)
                .setPositiveButton("Aceptar"){_,_->}.create()
            cancelDialog.show()
        }else{
            intentLP.putExtra("prestamos",listaPrestamos)
            startActivity(intentLP)
        }
    }
}