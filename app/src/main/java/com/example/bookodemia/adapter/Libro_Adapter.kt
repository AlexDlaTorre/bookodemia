package com.example.bookodemia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookodemia.DatosLibro.Datos_Libro
import com.example.bookodemia.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

class Libro_Adapter (val libros: MutableList<Datos_Libro>): RecyclerView.Adapter<Libro_Adapter.LibroHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Libro_Adapter.LibroHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LibroHolder(layoutInflater.inflate(R.layout.item_cardview_libros,parent,false))
    }

    override fun onBindViewHolder(holder: Libro_Adapter.LibroHolder, position: Int) {
        holder.render((libros[position]))
    }

    override fun getItemCount(): Int = libros.size
    class LibroHolder (val view: View): RecyclerView.ViewHolder(view){
        val cardView: MaterialCardView = view.findViewById(R.id.cardView_book)
        val textViewTitulo: TextView = view.findViewById(R.id.textView_titulo)
        val textViewAutor: TextView = view.findViewById(R.id.textView_autor)
        val textViewCategoria: TextView = view.findViewById(R.id.textView_categoria)

        fun render(libros: Datos_Libro){

            textViewTitulo.setText(libros.titulo)
            textViewAutor.setText(libros.autor)
            textViewCategoria.setText(libros.categoria)

            cardView.setOnClickListener{
                Snackbar.make(view,"Tap en ${libros.titulo}", Snackbar.LENGTH_SHORT).show()
            }
        }

    }

}