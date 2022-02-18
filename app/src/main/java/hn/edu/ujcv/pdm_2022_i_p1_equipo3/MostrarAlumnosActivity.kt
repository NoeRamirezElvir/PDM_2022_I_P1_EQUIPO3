package hn.edu.ujcv.pdm_2022_i_p1_equipo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import clases.AdapterAlumnos
import clases.Alumno
import clases.ModelAlumnos

import hn.edu.ujcv.pdm_2022_i_p1_equipo3.databinding.ActivityMostrarAlumnosBinding


class MostrarAlumnosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMostrarAlumnosBinding
    var listaAlumnos:ArrayList<Alumno> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarAlumnosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inicializar()
        var listView = findViewById<ListView>(R.id.listViewAlumno)
        var list = mutableListOf<ModelAlumnos>()


        for(valores in listaAlumnos){
            if(valores.genero == "Masculino"){
                list.add(ModelAlumnos(valores.nombre!!,valores.numeroCuenta!!.toString(),
                    valores.carrera!!,valores.fechaIngreso!!,valores.correo!!,valores.genero!!,
                R.drawable.ic_masculino))
            }else{
                list.add(ModelAlumnos(valores.nombre!!,valores.numeroCuenta!!.toString(),
                    valores.carrera!!,valores.fechaIngreso!!,valores.correo!!,valores.genero!!,
                    R.drawable.ic_femenino))
            }
        }
        listView.adapter = AdapterAlumnos(this,R.layout.row_alumnos,list)

    }
    private fun inicializar(){
        val intent = intent
        if(intent.getSerializableExtra("lista") != null){
            listaAlumnos = intent.getSerializableExtra("lista") as ArrayList<Alumno>
            listaAlumnos.sort()
        }
    }

}