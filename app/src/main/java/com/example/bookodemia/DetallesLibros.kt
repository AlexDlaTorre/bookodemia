package com.example.bookodemia

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class DetallesLibros : Fragment() {
    private var parent_view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalles_libros, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val botonRegresar2: Button = view.findViewById(R.id.button_regresar2)

        val intent2 = Intent (this@DetallesLibros.requireContext(), Pantalla_principal::class.java)

        botonRegresar2.setOnClickListener {
            startActivity(intent2)
        }

    }
}