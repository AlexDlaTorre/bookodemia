package com.example.bookodemia

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_registro.*
import kotlinx.android.synthetic.main.fragment_registro.view.*

class RegistroFragment : Fragment() {

    private var parent_view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botonRegresar: Button = view.findViewById(R.id.button_regresar)
        val botonCrearCuenta: Button = view.findViewById(R.id.button_crear_cuenta)
        val intent = Intent (this@RegistroFragment.requireContext(), MainActivity::class.java)

        botonRegresar.setOnClickListener {
            startActivity(intent)
        }

        botonCrearCuenta.setOnClickListener {
            startActivity(intent)
        }

        //texto.setText("Registra tu información")
        //parent_view = requireActivity().findViewById(android.R.id.content)
        //Snackbar.make(parent_view!!, "", Snackbar.LENGTH_SHORT).show()



        val textUsuario: String = till_usuario.editText?.text?.trim().toString()
        val textUsuario2: String = tiet_usuario.text?.trim().toString()

        tiet_usuario.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                if (editText.toString().trim().isEmpty()) {
                    till_usuario.setError("El campo es requerido")
                } else {
                    till_usuario.setErrorEnabled(false)
                    till_usuario.setError("")
                }
            }

        })
        val textCorreo: String = till_correo.editText?.text?.trim().toString()
        val textCorreo2: String = tiet_correo.text?.trim().toString()

        tiet_usuario.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                if (editText.toString().trim().isEmpty()) {
                    till_correo.setError("El campo es requerido")
                } else {
                    till_correo.setErrorEnabled(false)
                    till_correo.setError("")
                }
            }

        })

        val textContrasena: String = till_contrasena.editText?.text?.trim().toString()
        val textContrasena2: String = tiet_contrasena.text?.trim().toString()

        tiet_usuario.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                if (editText.toString().trim().isEmpty()) {
                    till_contrasena.setError("El campo es requerido")
                } else {
                    till_contrasena.setErrorEnabled(false)
                    till_contrasena.setError("")
                }
            }

        })

        val textConfirmarContrasena: String = till_confirmar_contrasena.editText?.text?.trim().toString()
        val textConfirmarContrasena2: String = tiet_confirmar_contrasena.text?.trim().toString()

        tiet_usuario.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                if (editText.toString().trim().isEmpty()) {
                    till_confirmar_contrasena.setError("El campo es requerido")
                } else {
                    till_confirmar_contrasena.setErrorEnabled(false)
                    till_confirmar_contrasena.setError("")
                }
            }

        })
        }
    }



