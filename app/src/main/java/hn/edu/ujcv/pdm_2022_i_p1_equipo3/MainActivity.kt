package hn.edu.ujcv.pdm_2022_i_p1_equipo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import clases.Alumno
import clases.Libro
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listaAlumnos:ArrayList<Alumno>? = ArrayList()
    var listaLibros:ArrayList<Libro>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializar()
        button.setOnClickListener{mostrarRegistroAlumnos()}
        btnMostrarAlumno.setOnClickListener{mostrarListaAlumnos()}
        btnIngresarLibros.setOnClickListener { mostrarRegistroLibros() }
        btnMostrarLibros.setOnClickListener { mostrarListaLibros() }
    }
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
    private fun inicializar(){
        val intent = intent
        if(intent.getSerializableExtra("lista") != null){
            listaAlumnos = intent.getSerializableExtra("lista") as ArrayList<Alumno>
        }
        val intent2 = intent
        if(intent2.getSerializableExtra("libros") != null){
            listaLibros = intent.getSerializableExtra("libros") as ArrayList<Libro>
        }
    }

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
}