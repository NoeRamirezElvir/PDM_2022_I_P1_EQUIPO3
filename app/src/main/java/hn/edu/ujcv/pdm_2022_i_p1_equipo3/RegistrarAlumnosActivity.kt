package hn.edu.ujcv.pdm_2022_i_p1_equipo3

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import clases.Alumno
import hn.edu.ujcv.pdm_2022_i_p1_equipo3.databinding.ActivityRegistrarAlumnosBinding
import kotlinx.android.synthetic.main.activity_registrar_alumnos.*
import java.util.*
import kotlin.Exception
import kotlin.collections.ArrayList


class RegistrarAlumnosActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener{
    private lateinit var binding: ActivityRegistrarAlumnosBinding
    var day        = 0
    var month      = 0
    var year       = 0
    var saveDay    = 0
    var saveMonth  = 0
    var saveYear   = 0
    var listaAlumnos:ArrayList<Alumno> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarAlumnosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        inicializar()
        //
        val spinner = binding.spnGenero
        val lista = resources.getStringArray(R.array.genero)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adaptador
        binding.spnGenero.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long
            ) {
                if(lista[p2] == "Femenino"){
                    binding.ivAlumnos.setImageResource(R.drawable.ic_femenino)
                }else{
                    binding.ivAlumnos.setImageResource(R.drawable.ic_masculino)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        //
        btnRegistrar.setOnClickListener {
            if(!validarBoton()){
                Toast.makeText(this, "Comprobar los datos", Toast.LENGTH_SHORT).show()
            }else{
                val alumno:Alumno = Alumno()
                asignarDatos(alumno,spinner)
                dialogo(alumno)

                binding.btnMostrarAlumno.setOnClickListener{ enviarMostrar() }
                binding.btnRegresar.setOnClickListener { enviarMain() }
            }
        }
    }

    private fun inicializar() {
        fecha()
        validarDatos()
        val intent = intent
        if(intent.getSerializableExtra("lista") != null){
            listaAlumnos = intent.getSerializableExtra("lista") as ArrayList<Alumno>
        }
    }
    private fun enviarMostrar(){
        val intent = Intent(this,MostrarAlumnosActivity::class.java)
        intent.putExtra("lista",listaAlumnos)
        startActivity(intent)
    }
    private fun enviarMain(){
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("lista",listaAlumnos)
        startActivity(intent)
    }
    private fun asignarDatos(alumno: Alumno,spinner: Spinner){
        alumno.fechaIngreso = binding.tiltxtFecha.text.toString()
        alumno.numeroCuenta = binding.tiltxtNumeroCuenta.text.toString().toLong()
        alumno.nombre = binding.tiltxtNombre.text.toString()
        alumno.carrera = binding.tiltxtCarrera.text.toString()
        alumno.correo = binding.tiltxtCorreo.text.toString()
        alumno.genero = spinner.selectedItem.toString()
    }
    private fun dialogo(alumno: Alumno){
        val dialog = AlertDialog.Builder(this)
            .setTitle("¿Desea registrar el siguiente estudiante?")
            .setMessage("${alumno.numeroCuenta}\n" +
                    " ${alumno.nombre}\n" +
                    " ${alumno.fechaIngreso}\n" +
                    "${alumno.genero}\n" +
                    " ${alumno.correo}\n" +
                    " ${alumno.carrera}.")
            .setIcon(R.drawable.icono_agregar)
            .setPositiveButton("Si"){_,_ ->
                listaAlumnos.add(alumno)
                Toast.makeText(this,"Se ha registrado correctamente",Toast.LENGTH_SHORT).show()
                limpiar()
            }
            .setNegativeButton("No"){_,_ ->
                Toast.makeText(this,"No se ha registrado",Toast.LENGTH_SHORT).show()
            }.create()
        dialog.show()
    }
    private fun validarDatos(){
        numeroCuentaFocused()
        nombreFocused()
        fechaFocused()
        carreraFocused()
        correoFocused()
    }
    private fun limpiar(){
        binding.tiltxtFecha.text = null
        binding.tiltxtNumeroCuenta.text = null
        binding.tiltxtNombre.text = null
        binding.tiltxtCarrera.text = null
        binding.tiltxtCorreo.text = null

    }
    private fun validarBoton():Boolean{
        when{
            binding.tiltxtNumeroCuenta.text.isNullOrEmpty() -> return false
            binding.tiltxtNumeroCuenta.text!!.length < 10 -> return false
            binding.tiltxtNumeroCuenta.text!!.length >10 ->return false
            binding.tiltxtNombre.text.isNullOrEmpty() -> return false
            binding.tiltxtNombre.text!!.length < 3 -> return false
            binding.tiltxtNombre.text!!.length > 40 -> return false
            binding.tiltxtCarrera.text.isNullOrEmpty() -> return false
            binding.tiltxtCarrera.text!!.length < 3 -> return false
            binding.tiltxtCarrera.text!!.length > 50 -> return false
            binding.tiltxtFecha.text.isNullOrEmpty() -> return false
            binding.tiltxtFecha.text!!.length < 5 -> return false
            binding.tiltxtFecha.text!!.length > 20 -> return false
            binding.tiltxtCorreo.text.isNullOrEmpty() -> return false
            binding.tiltxtCorreo.text!!.length < 5 -> return false
            binding.tiltxtCorreo.text!!.length > 50 -> return false
            !Patterns.EMAIL_ADDRESS.matcher(binding.tiltxtCorreo.text!!).matches() -> return false
            else -> return true
        }
    }
    private fun numeroCuentaFocused(){
        binding.tiltxtNumeroCuenta.doOnTextChanged { _, _, _, _ ->
            binding.tilNumeroCuenta.helperText = validarCuenta()
        }
    }
    private fun nombreFocused(){
        binding.tiltxtNombre.doOnTextChanged { _, _, _, _ ->
            binding.tilNombre.helperText = validarNombre()
        }
    }
    private fun carreraFocused(){
        binding.tiltxtCarrera.doOnTextChanged { _, _, _, _ ->
            binding.tilCarrera.helperText = validarCarrera()
        }
    }
   private fun correoFocused(){
       binding.tiltxtCorreo.doOnTextChanged { _, _, _, _ ->
           binding.tilCorreo.helperText = validarCorreo()
       }
   }
    private fun fechaFocused(){
        binding.tiltxtFecha.doAfterTextChanged {
            binding.tilFecha.helperText = validarFecha()
        }
    }
    private fun validarFecha():String?{
        var mensaje:String? = null
        try{
            if(binding.tiltxtFecha.text.isNullOrEmpty()){
                throw Exception("La fecha de cuenta está vacía.")
            }
            Alumno().ValidacionesAlumnos().validarFecha(binding.tiltxtFecha.text.toString())
        }catch (ex:Exception){
            mensaje = ex.message
        }finally {
            return  mensaje
        }
    }
    private fun validarCuenta():String? {
        var mensaje:String? = null
        try{
            if(binding.tiltxtNumeroCuenta.text.isNullOrEmpty()){
                throw Exception("El número de cuenta está vacío.")
            }
            Alumno().ValidacionesAlumnos().validarCuenta(binding.tiltxtNumeroCuenta.text.toString().toLong())
        }catch (ex:Exception){
            mensaje = ex.message
        }finally {
            return  mensaje
        }
    }
    private fun validarNombre():String?{
        var mensaje:String? = null
        try{
            if(binding.tiltxtNombre.text.isNullOrEmpty()){
                throw Exception("El nombre de cuenta está vacío.")
            }
            Alumno().ValidacionesAlumnos().validarNombre(binding.tiltxtNombre.text.toString())
        }catch (ex:Exception){
            mensaje = ex.message
        }finally {
            return  mensaje
        }
    }
    private fun validarCarrera():String?{
        var mensaje:String? = null
        try{
            if(binding.tiltxtCarrera.text.isNullOrEmpty()){
                throw Exception("La carrera está vacía.")
            }
            Alumno().ValidacionesAlumnos().validarCarrera(binding.tiltxtCarrera.text.toString())
        }catch (ex:Exception){
            mensaje = ex.message
        }finally {
            return  mensaje
        }
    }
    private fun validarCorreo():String?{
        var mensaje:String? = null
        try{
            if(binding.tiltxtCorreo.text.isNullOrEmpty()){
                throw Exception("La carrera está vacía.")
            }
            Alumno().ValidacionesAlumnos().validarCorreo(binding.tiltxtCorreo.text.toString())
        }catch (ex:Exception){
            mensaje = ex.message
        }finally {
            return  mensaje
        }
    }
    private fun obtenerFecha(){
        val cal = Calendar.getInstance()
        day    = cal.get(Calendar.DAY_OF_MONTH)
        month  = cal.get(Calendar.MONTH)
        year   = cal.get(Calendar.YEAR)
    }
    private fun fecha(){
      tiltxtFecha.setOnClickListener{
          obtenerFecha()
          DatePickerDialog(this,this,year,month,day).show()
      }
    }
    @SuppressLint("SetTextI18n")
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        saveDay   = p3
        saveMonth = p2 + 1
        saveYear  = p1
        tiltxtFecha.setText("$saveDay/$saveMonth/$saveYear")
    }
}