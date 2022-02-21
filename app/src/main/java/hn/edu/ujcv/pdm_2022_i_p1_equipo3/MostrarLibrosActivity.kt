package hn.edu.ujcv.pdm_2022_i_p1_equipo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import clases.*


class MostrarLibrosActivity : AppCompatActivity() {
    var listaLibros:ArrayList<Libro> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_libros)
        inicializar()
        var listView = findViewById<ListView>(R.id.lvLibros)
        var list = mutableListOf<ModelLibro>()

        for (libro in listaLibros) {
            list.add(ModelLibro(libro.isbn!!, libro.nombre!!, libro.autor!!,
                libro.fechaPublicacion!!, libro.editorial!!, R.drawable.ic_book_icon))
        }
        listView.adapter = AdapterLibro(this, R.layout.list_item_libro,list)
    }

    private fun inicializar() {
        val intent = intent
        if (intent.getSerializableExtra("libros") != null) {
            listaLibros = intent.getSerializableExtra("libros") as ArrayList<Libro>
            listaLibros.sort()
        }
    }

}

