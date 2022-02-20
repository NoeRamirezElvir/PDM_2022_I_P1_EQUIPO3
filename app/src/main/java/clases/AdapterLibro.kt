package clases

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import hn.edu.ujcv.pdm_2022_i_p1_equipo3.R

class AdapterLibro(var myContext: Context, var resources:Int, var listaLibros: List<ModelLibro>)
    : ArrayAdapter<ModelLibro>(myContext, resources,listaLibros) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater:LayoutInflater = LayoutInflater.from(myContext)
        val view:View = inflater.inflate(resources, null)

        val imageView: ImageView = view.findViewById(R.id.ivLibro)
        val titulo: TextView = view.findViewById(R.id.tituloLibro)
        val isbn_: TextView = view.findViewById(R.id.isbnLibro)
        val autor: TextView = view.findViewById(R.id.autorLibro)
        val fecha:TextView = view.findViewById(R.id.fechaLibro)
        val editorial:TextView = view.findViewById(R.id.editorialLibro)

        var mItem:ModelLibro = listaLibros[position]

        imageView.setImageDrawable(myContext.resources.getDrawable(mItem.imageId))
        titulo.text = mItem.Nombre
        isbn_.text = listaLibros[position].NumeroLibro.toString()
        autor.text = listaLibros[position].Autor
        editorial.text = listaLibros[position].Editorial
        fecha.text = listaLibros[position].Fecha

        return view
    }
}