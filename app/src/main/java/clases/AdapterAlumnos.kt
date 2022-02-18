package clases

import android.content.Context
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import hn.edu.ujcv.pdm_2022_i_p1_equipo3.R

class AdapterAlumnos(var mCxtx:Context,var resources:Int,var items:List<ModelAlumnos>)
    :ArrayAdapter<ModelAlumnos>(mCxtx,resources,items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCxtx)
        val view:View = layoutInflater.inflate(resources,null)

        val vistaImagen:ImageView = view.findViewById(R.id.ivAlumno)
        val vistaNombre:TextView = view.findViewById(R.id.txvNombre)
        val vistaCuenta:TextView = view.findViewById(R.id.txvNumeroCuenta)
        val vistaCarrera:TextView = view.findViewById(R.id.txvCarrera)
        val vistaFecha:TextView = view.findViewById(R.id.txvFechaIngreso)
        val vistaCorreo:TextView = view.findViewById(R.id.txvCorreo)

        var mItem:ModelAlumnos = items[position]
        vistaImagen.setImageDrawable(mCxtx.resources.getDrawable(mItem.imagen))
        vistaNombre.text = mItem.nombre
        vistaCuenta.text = mItem.cuenta
        vistaCarrera.text = mItem.carrera
        vistaFecha.text  = mItem.fecha
        vistaCorreo.text = mItem.correo

        return view
    }

}