package hn.edu.ujcv.pdm_2022_i_p1_equipo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import clases.*
import kotlinx.android.synthetic.main.activity_mostar_prestamos.*

class MostarPrestamosActivity : AppCompatActivity() {
    var listaPrestamos:ArrayList<Prestamo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostar_prestamos)
        inicializar()
        var listView = findViewById<ListView>(R.id.listViewPrestamos)
        listar(listView)
        btnBuscar.setOnClickListener { buscar(listView) }
        btnListar.setOnClickListener { listar(listView) }
    }

    private fun listar(listView: ListView) {
        var list = mutableListOf<ModelPrestamo>()
        for (prestamo in listaPrestamos) {
            list.add(ModelPrestamo(prestamo.numeroPrestamo!!, prestamo.fechaPrestamo!!, prestamo.numeroCuenta!!,
                prestamo.numeroLibro!!, prestamo.fechaDev!!, R.drawable.logo_p)
            )
        }
        listView.adapter = AdapterPrestamo(this, R.layout.row_prestamos,list)
    }

    private fun buscar(listView :ListView) {
        if (txtNumeroC.text.isNullOrEmpty() || txtNumeroC.text!!.length != 10){
            Toast.makeText(this, "No. Cuenta Inválido", Toast.LENGTH_SHORT).show()
            txtNumeroC.text == null
        }else{
            var numero = txtNumeroC.text.toString().toLong()
            var list = mutableListOf<ModelPrestamo>()
            var condicion = false
            for (prestamo in listaPrestamos) {
                condicion = false
                if (numero == prestamo.numeroCuenta){
                    list.add(ModelPrestamo(prestamo.numeroPrestamo!!, prestamo.fechaPrestamo!!, prestamo.numeroCuenta!!,
                        prestamo.numeroLibro!!, prestamo.fechaDev!!, R.drawable.logo_p)
                    )
                    listView.adapter = AdapterPrestamo(this, R.layout.row_prestamos,list)
                    break
                }else{
                    condicion = true
                }
            }
            if (condicion){
                Toast.makeText(this, "No. cuenta no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inicializar(){
        val intent = intent
        if(intent.getSerializableExtra("prestamos") != null){
            listaPrestamos = intent.getSerializableExtra("prestamos") as ArrayList<Prestamo>
            listaPrestamos.sort()
        }
    }
}