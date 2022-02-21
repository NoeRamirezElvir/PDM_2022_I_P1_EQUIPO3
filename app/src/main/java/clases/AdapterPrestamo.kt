package clases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import hn.edu.ujcv.pdm_2022_i_p1_equipo3.R

class AdapterPrestamo(var myContext: Context, var resources:Int, var listaPrestamos: List<ModelPrestamo>)
    : ArrayAdapter <ModelPrestamo> (myContext,resources,listaPrestamos) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(myContext)
        val view:View = inflater.inflate(resources, null)

        val imageView: ImageView = view.findViewById(R.id.ivPrestamo)
        val noPrestamo: TextView = view.findViewById(R.id.txvNoPrestamo)
        val fechaPrestamo: TextView = view.findViewById(R.id.txvFechaP)
        val noCuenta: TextView = view.findViewById(R.id.txvNoCuentaP)
        val noLibro: TextView = view.findViewById(R.id.txvNoLibro)
        val fechaPD: TextView = view.findViewById(R.id.txvFechaPD)

        var mItem:ModelPrestamo = listaPrestamos[position]

        imageView.setImageDrawable(myContext.resources.getDrawable(mItem.img))
        noPrestamo.text    = mItem.NumeroPrestamo.toString()
        fechaPrestamo.text = mItem.FechaP
        noCuenta.text      = mItem.NoCuenta.toString()
        noLibro.text       = mItem.NoLibro.toString()
        fechaPD.text       = mItem.FechaPD

        return view
    }

}