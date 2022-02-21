package hn.edu.ujcv.pdm_2022_i_p1_equipo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import clases.*

class MostarPrestamosActivity : AppCompatActivity() {
    var listaPrestamos:ArrayList<Prestamo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostar_prestamos)
        inicializar()
        var listView = findViewById<ListView>(R.id.listViewPrestamos)
        var list = mutableListOf<ModelPrestamo>()

        for (prestamo in listaPrestamos) {
            list.add(ModelPrestamo(prestamo.numeroPrestamo!!, prestamo.fechaPrestamo!!, prestamo.numeroCuenta!!,
                prestamo.numeroLibro!!, prestamo.fechaDev!!, R.drawable.logo_p)
            )
        }
        listView.adapter = AdapterPrestamo(this, R.layout.row_prestamos,list)
    }
    private fun inicializar(){
        val intent = intent
        if(intent.getSerializableExtra("prestamos") != null){
            listaPrestamos = intent.getSerializableExtra("prestamos") as ArrayList<Prestamo>
            listaPrestamos.sort()
        }
    }
}