package com.example.bookodemia.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookodemia.Pantalla_principal
import com.example.bookodemia.R
import com.example.bookodemia.dataclass.Book
//import com.example.bookodemia.dataclass.Book
import com.google.android.material.card.MaterialCardView

class Libro_Adapter(val activity: Activity, val books: MutableList<Book>): RecyclerView.Adapter<Libro_Adapter.LibroHolder>() {
    //class Libro_Adapter (val libros: MutableList<Datos_Libro>): RecyclerView.Adapter<Libro_Adapter.LibroHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroHolder {
   // override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Libro_Adapter.LibroHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LibroHolder(layoutInflater.inflate(R.layout.item_cardview_libros,parent,false))
    }
   //override fun onBindViewHolder(holder: Libro_Adapter.LibroHolder, position: Int){
   override fun onBindViewHolder(holder: LibroHolder, position: Int) {
        val book = books.get(position)
       println("aquiiii estan los libros ${book}")
       with(holder){
           textViewTitulo.setOnClickListener{
               val bundle = Bundle()
               bundle.putSerializable("book",book)
               val intent = Intent(activity,Pantalla_principal::class.java)
               intent.putExtras(bundle)
               activity.startActivity(intent)
           }
           textViewTitulo.text = book.attributes.title
           textViewContent.text = book.attributes.content

       }
       //holder.render((libros[position]))
    }

    override fun getItemCount(): Int = books.size

    class LibroHolder (val view: View): RecyclerView.ViewHolder(view){
        val cardView: MaterialCardView = view.findViewById(R.id.cardView_book)
        val textViewTitulo: TextView = view.findViewById(R.id.textView_titulo)
        val textViewContent: TextView = view.findViewById(R.id.textView_content)
        //val textViewCategoria: TextView = view.findViewById(R.id.textView_categoria)

//        fun render(books: Datos_Libro){
//
//            textViewTitulo.setText(books.titulo)
//            textViewContent.setText(books.content)
//            //textViewCategoria.setText(libros.categoria)
//
//            cardView.setOnClickListener{
//                Snackbar.make(view,"Tap en ${books.titulo}", Snackbar.LENGTH_SHORT).show()
//            }
//        }

    }

}