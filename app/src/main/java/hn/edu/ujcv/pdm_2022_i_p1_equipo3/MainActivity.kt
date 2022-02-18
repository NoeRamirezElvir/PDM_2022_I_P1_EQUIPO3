package hn.edu.ujcv.pdm_2022_i_p1_equipo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import clases.Alumno
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listaAlumnos:ArrayList<Alumno>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializar()
        button.setOnClickListener{mostrarRegistroAlumnos()}
        btnMostrarAlumno.setOnClickListener{mostrarListaAlumnos()}
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
    }
}