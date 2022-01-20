package com.example.bookodemia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textEmail: String = til_email1.editText?.text?.trim().toString()
        val textEmail2: String = tiet_email1.text?.trim().toString()

        tiet_email1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                if (editText.toString().trim().isEmpty()) {
                    til_email1.setError("El campo es requerido")
                } else {
                    til_email1.setErrorEnabled(false)
                    til_email1.setError("")
                }
            }

        })
        val textPassword1: String = till_password1.editText?.text?.trim().toString()
        val textPassword2: String = tiet_password1.text?.trim().toString()

        tiet_password1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                if (editText.toString().trim().isEmpty()) {
                    till_password1.setError("El campo es requerido")
                } else {
                    till_password1.setErrorEnabled(false)
                    till_password1.setError("")
                }
            }

        })

        val textButton: Button = findViewById(R.id.text_button)

        textButton.setOnClickListener {
            val fragmentRegistroKoder = RegistroFragment()
            val fragmentManager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.add(android.R.id.content, fragmentRegistroKoder)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        val botonCrearCuenta: Button = findViewById(R.id.button_login)
        val intent = Intent (this, Pantalla_principal::class.java)

        botonCrearCuenta.setOnClickListener {
            startActivity(intent)
        }

    }
}