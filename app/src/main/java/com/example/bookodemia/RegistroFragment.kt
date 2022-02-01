package com.example.bookodemia

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookodemia.extra.iniciarSesion
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_registro.*
import kotlinx.android.synthetic.main.fragment_registro.view.*
import org.json.JSONObject

class RegistroFragment : Fragment() {

    private val TAG = RegistroFragment::class.qualifiedName
    private var parent_view: View? = null
//    private lateinit var till_usuario: TextInputLayout
//    private lateinit var tiet_usuario: TextInputEditText
//    private lateinit var till_correo: TextInputLayout
//    private lateinit var tiet_correo: TextInputEditText
//    private lateinit var till_contrasena: TextInputLayout
//    private lateinit var tiet_contrasena: TextInputEditText
//    private lateinit var till_confirmar_contrasena: TextInputLayout
//    private lateinit var tiet_confirmar_contrasena: TextInputEditText
//    private lateinit var button_crear_cuenta: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appContext = context?.applicationContext
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botonRegresar: Button = view.findViewById(R.id.button_regresar)
        val botonCrearCuenta: Button = view.findViewById(R.id.button_crear_cuenta)
        val till_usuario:TextInputLayout = view.findViewById(R.id.till_usuario)
        val tiet_usuario:TextInputEditText = view.findViewById(R.id.tiet_usuario)
        val till_correo:TextInputLayout = view.findViewById(R.id.till_correo)
        val tiet_correo:TextInputEditText = view.findViewById(R.id.tiet_correo)
        val till_contrasena:TextInputLayout = view.findViewById(R.id.till_contrasena)
        val tiet_contrasena:TextInputEditText = view.findViewById(R.id.tiet_contrasena)
        val till_confirmar_contrasena:TextInputLayout = view.findViewById(R.id.till_confirmar_contrasena)
        val tiet_confirmar_contrasena:TextInputEditText = view.findViewById(R.id.tiet_confirmar_contrasena)
        val intent = Intent(this@RegistroFragment.requireContext(), MainActivity::class.java)

        validacionesDeRegistro()

        botonRegresar.setOnClickListener {
            startActivity(intent)
        }
        fun realizarPeticion() {
            val json = JSONObject()
            json.put("name", tiet_usuario.text)
            json.put("email", tiet_correo.text)
            json.put("password", tiet_contrasena.text)
            json.put("password_confirmation", tiet_confirmar_contrasena.text)
            json.put("device_name","User's phone")

            val cola = Volley.newRequestQueue(context?.applicationContext)
            val peticion = JsonObjectRequest(
                Request.Method.POST,
                getString(R.string.url_servidor) + getString(R.string.api_registro),
                json,
                { response ->
                    Log.d(TAG,response.toString())

                },
                { error ->
                    Log.e(TAG,error.toString())
                })
            cola.add(peticion)
        }

        botonCrearCuenta.setOnClickListener {
            startActivity(intent)
            realizarPeticion()
        }


    }
//    val textView2: TextView = view.findViewById(R.id.textView2)
//    textView2.setText("Registra tu información")
//    parent_view = requireActivity().findViewById(android.R.id.content)
//    Snackbar.make(parent_view!!, "", Snackbar.LENGTH_SHORT).show()

    fun validacionesDeRegistro(){

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

        val textConfirmarContrasena: String =
            till_confirmar_contrasena.editText?.text?.trim().toString()
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



