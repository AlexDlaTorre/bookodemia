package com.example.bookodemia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookodemia.DatosLibro.Datos_Libro
import com.example.bookodemia.adapter.Libro_Adapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

class Pantalla_principal : AppCompatActivity() {

    val listLibros: MutableList<Datos_Libro> = mutableListOf()
    var libroAdapter = Libro_Adapter(listLibros)
    var parent_view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        initRecyclerLibros()

    }

    private fun initRecyclerLibros(){
        listLibros.add(Datos_Libro("Pedro Páramo","Juan Rulfo","Novela"))
        listLibros.add(Datos_Libro("Diario de Ana Frank","Ana Frank","Autobiografía"))
        listLibros.add(Datos_Libro("Corazón","Edmondo De Amicis","Novela"))
        listLibros.add(Datos_Libro("El retrato de Dorian Gray","Oscar Wilde","Novela"))



        recycler_coleccion_libros.layoutManager = LinearLayoutManager(this)
        recycler_coleccion_libros.setHasFixedSize(true)
        libroAdapter = Libro_Adapter(listLibros)
        recycler_coleccion_libros.adapter = libroAdapter

val DetallesLibrosButton: Button = findViewById(R.id.button_libros)

DetallesLibrosButton.setOnClickListener {
    val fragmentDetallesLibros = DetallesLibros()
    val fragmentManager2: FragmentManager = supportFragmentManager
    val transaction: FragmentTransaction = fragmentManager2.beginTransaction()
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    transaction.add(android.R.id.content, fragmentDetallesLibros)
    transaction.addToBackStack(null)
    transaction.commit()
}

        /*val textoNombre: String = findViewById(R.id.textView_nombre_usuario)
        textoNombre.setText("Koders")
        parent_view = requireActivity().findViewById(android.R.id.content)
        Snackbar.make(parent_view!!, "", Snackbar.LENGTH_SHORT).show()*/
}


}