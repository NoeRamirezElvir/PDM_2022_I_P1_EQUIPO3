package hn.edu.ujcv.pdm_2022_i_p1_equipo3

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import clases.Alumno
import clases.Prestamo
import hn.edu.ujcv.pdm_2022_i_p1_equipo3.databinding.ActivityIngresarLibrosBinding
import hn.edu.ujcv.pdm_2022_i_p1_equipo3.databinding.ActivityIngresarPrestamoBinding
import kotlinx.android.synthetic.main.activity_ingresar_prestamo.*

class IngresarPrestamoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIngresarPrestamoBinding
    var listaPrestamos:ArrayList<Prestamo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresarPrestamoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inicializar()
        btnRegistarPrestamo.setOnClickListener {
            if (guardar() == null){
                Toast.makeText(this, "Confirmar Datos", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,guardar().toString(), Toast.LENGTH_SHORT).show()

            }
            binding.btnRegresarP.setOnClickListener { regresar() }
            binding.btnMostarPrestamo.setOnClickListener { mostrar() }
        }

    }

    private fun inicializar() {
        fecha()
        val intent = intent
        if(intent.getSerializableExtra("prestamos") != null){
            listaPrestamos = intent.getSerializableExtra("prestamos") as ArrayList<Prestamo>
        }
    }
    private fun mostrar() {
        if (listaPrestamos.isNullOrEmpty()){
            val cancelDialog = AlertDialog.Builder (this)
                .setTitle("No hay registros para Mostrar")
                .setMessage("Falta de Información")
                .setIcon(R.drawable.icono_cancelar)
                .setPositiveButton("Aceptar"){_,_->}.create()
            cancelDialog.show()
        }else{
            val intent = Intent(this, MostarPrestamosActivity::class.java)
            intent.putExtra("prestamos", listaPrestamos)
            startActivity(intent)
        }
    }
    private fun regresar(){
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("prestamos",listaPrestamos)
        startActivity(intent)
    }

    private fun guardar() : String?{
        var mensaje: String? = null
        var prestamo : Prestamo = Prestamo()
        try {
            prestamo.numeroPrestamo = tiltxtNumeroPrestamo.text.toString().toLong()
            prestamo.fechaPrestamo  = tiltxtFechaP.text.toString()
            prestamo.numeroCuenta   = tiltxtNumeroCuentaP.text.toString().toLong()
            prestamo.numeroLibro    = tiltxtNumeroLibroP.text.toString().toLong()
            prestamo.fechaDev       = tiltxtFechaPD.text.toString()
            mostrarDialog(prestamo)
        }catch (e: Exception){
            mensaje = e.message.toString()
        }finally {
            return mensaje
        }
    }

    @SuppressLint("NewApi")
    private fun fecha() {
        val calendar = Calendar.getInstance()
        val año = calendar.get(Calendar.YEAR)
        val mes = calendar.get(Calendar.MONTH)
        val dia = calendar.get(Calendar.DAY_OF_MONTH)
        var month:Int
        tiltxtFechaP.setOnClickListener {
            val date_dialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMes, mDia ->
                    month = mMes + 1
                    tiltxtFechaP.setText("" + mDia + "/" + month + "/" + mYear)
                    tiltxtFechaPD.setText("" + (mDia+3) + "/" + month + "/" + mYear)
                }, año, mes, dia)
            date_dialog.show()
        }
    }

    private fun limpiar(){
        tiltxtNumeroPrestamo.text  = null
        tiltxtFechaP.text          = null
        tiltxtNumeroCuentaP.text   = null
        tiltxtNumeroLibroP.text    = null
        tiltxtFechaPD.text         = null

    }
    private fun mostrarDialog(prestamo: Prestamo) {
        var mensaje = "No. Préstamo: " + tiltxtNumeroPrestamo.text
        mensaje += "\nFecha: " + tiltxtFechaP.text
        mensaje += "\nNo. Cuenta: " + tiltxtNumeroCuentaP.text
        mensaje += "\nNo. Libro: " + tiltxtNumeroLibroP.text
        mensaje += "\nDevolución: " + tiltxtFechaPD.text
        val dialog = AlertDialog.Builder(this)
            .setTitle("Desea registrar este Préstamo?")
            .setMessage(mensaje)
            .setPositiveButton("Si") {_,_ ->
                listaPrestamos.add(prestamo)
                Toast.makeText(this, "Guardado Exítosamente", Toast.LENGTH_SHORT).show()
                limpiar()
            }
            .setNegativeButton("No"){_,_ ->
                Toast.makeText(this, "No se guardó el registro", Toast.LENGTH_SHORT).show()
            }.create()
        dialog.show()
    }
}