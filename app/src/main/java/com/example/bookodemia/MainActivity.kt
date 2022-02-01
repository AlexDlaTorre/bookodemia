package com.example.bookodemia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookodemia.dataclass.Books
import com.example.bookodemia.extra.estaEnLinea
import com.example.bookodemia.extra.iniciarSesion
import com.example.bookodemia.extra.mensajeEmergente
import com.example.bookodemia.extra.validarSesion
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var TAG = MainActivity::class.qualifiedName
//    private lateinit var til_email1: TextInputLayout
//    private lateinit var tiet_email1: TextInputEditText
//    private lateinit var til_password1: TextInputLayout
//    private lateinit var tiet_password1: TextInputEditText
//    private lateinit var button_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()
        irAregistrar()
        validarCorreo()
        validarPassword()
        //pruebaJson()
        //pruebaLibros()
        pruebaLibros2()
    }

//    fun pruebaJson() {
//        val cadena = """
//        {
//        "plain-text-token": "3|eEy7eoWviCYveN7GFkzp8a31nvtf3ZGX4QfpXUwH"
//        }
//""".trimIndent()
//        //agregar una variable a un json object
//        val jsonObject = JSONObject(cadena)
//        Log.e(TAG, jsonObject.getString("plain-text-token"))
//
//        //como hacer un jsonObject vacío
//        val jsonUsuario = JSONObject()
//        //añadir atributos al jsonObject vacío
//        jsonUsuario.put("name", "ulises")
//        jsonUsuario.put("correo", "ulises@kodemia.com")
//        jsonUsuario.put("telefono", "2461343823")
//        //Imprimir jsonObject como string
//        Log.d(TAG, jsonUsuario.toString())
//
//
//        //Otro ejemplo a parte con Gson
//        val cadenaDos = """
//            {
//            "nombre":"Ale",
//            "telefono":4493860677,
//            "casada":true,
//            "cuotaHora":150.28,
//            "diasDeSemanaTrabajo":[
//            "Lunes",
//            "Martes",
//            "Miercoles",
//            "Jueves",
//            Viernes"
//            ],
//            "observacion":[
//            {"fecha":"25/01/2021","comentario":"Todo bien"},
//            {"fecha":"02/01/2021","comentario":"Llegó tarde"}
//            ]
//            }
//            """.trimIndent()
//        try{
//            //Empleado le indica que tipo de estructura va a ocupar para hacer la serializacion
//            val empleado = Gson().fromJson(cadenaDos, Empleado::class.java)
//            Log.d(TAG,empleado.nombre)
//            Log.d(TAG,empleado.diasDeSemanaTrabajo.size.toString())
//            for(observacion in empleado.observacion){
//                Log.d(TAG,observacion.fecha)
//                Log.d(TAG,observacion.comentarios)
//            }
//        }
//        catch (e:Exception){
//            Log.e(TAG,e.toString())
//        }
//
//        //otro ejemplo si el objeto de arriba fuera un array de objetos iguales
//        val cadenaTres = """
//            [
//            {
//            "nombre":"Ale",
//            "telefono":4493860677,
//            "casada":true,
//            "cuotaHora":150.28,
//            "diasDeSemanaTrabajo":[
//            "Lunes",
//            "Martes",
//            "Miercoles",
//            "Jueves",
//            Viernes"
//            ],
//            "observacion":[
//            {"fecha":"25/01/2021","comentario":"Todo bien"},
//            {"fecha":"02/01/2021","comentario":"Llegó tarde"}
//            ]
//            },{
//            "nombre":"Fabian",
//            "telefono":4493860677,
//            "casada":true,
//            "cuotaHora":150.28,
//            "diasDeSemanaTrabajo":[
//            "Lunes",
//            "Martes",
//            "Miercoles",
//            "Jueves",
//            Viernes"
//            ],
//            "observacion":[
//            {"fecha":"25/01/2021","comentario":"Todo bien"},
//            {"fecha":"02/01/2021","comentario":"Llegó tarde"}
//            ]
//            }
//            ]
//            """.trimIndent()
//        val empleados = Gson().fromJson(cadenaTres,Array<Empleado>::class.java)
//        for(empleado in empleados){
//            Log.d(TAG,empleado.nombre)
//        }
//
//        //otro ejemplo ahora con Serialization (recomendada)
//        //preguntar no se ve nombre
//        val cadenaCuatro = """
//            {
//            "nombre":"Pablo",
//            "telefono":4493860677,
//            "casada":true,
//            "cuota_hora":150.28,
//            "dias-De-Semana-Trabajo":[
//            "Lunes",
//            "Martes",
//            "Miercoles",
//            "Jueves",
//            Viernes"
//            ],
//            "observacion":[
//            {"fecha":"25/01/2021","comentario":"Todo bien"},
//            {"fecha":"02/01/2021","comentario":"Llegó tarde"}
//            ]
//            }
//            """.trimIndent()
//        val empleadoSerialization = Json.decodeFromString<Empleado>(cadenaCuatro)
//        Log.d(TAG,empleadoSerialization.nombre)
//    }

//    fun pruebaLibros() {
//        val cadenaLibros = """
//        {
//          "data": [
//            {
//              "type": "books",
//              "id": "venganza-nuestra",
//              "attributes": {
//                "title": "Vengaza Nuestra",
//                "slug": "venganza-nuestra",
//                "content": "Narrativa sobre la postconquista de México",
//                "created-at": "2021-11-06T18:41:14+00:00",
//                "updated-at": "2021-11-06T18:41:14+00:00"
//              },
//              "relationships": {
//                "authors": {
//                  "links": {
//                    "self": "https://bookstore.test/api/v1/books/venganza-nuestra/relationships/authors",
//                    "related": "https://bookstore.test/api/v1/books/venganza-nuestra/authors"
//                  }
//                },
//                "categories": {
//                  "links": {
//                    "self": "https://bookstore.test/api/v1/books/venganza-nuestra/relationships/categories",
//                    "related": "https://bookstore.test/api/v1/books/venganza-nuestra/categories"
//                  }
//                }
//              },
//              "links": {
//                "self": "https://bookstore.test/api/v1/books/venganza-nuestra"
//              }
//            }
//          ]
//        }
//        """.trimIndent()
//        Log.d(TAG,"Aqui")
//        val books = Json.decodeFromString<Data>(cadenaLibros)
//        for(books in Books){
//            Log.d(TAG,books.attributes.title)
//        }
//
//
//    }


    fun init() {

        val btn_login = findViewById<Button>(R.id.button_login)
        val tiet_email1 = findViewById<TextInputEditText>(R.id.tiet_email1)
        val tiet_password1 = findViewById<TextInputEditText>(R.id.tiet_password1)

        btn_login.setOnClickListener {
            val cola = Volley.newRequestQueue(applicationContext)
            val json = JSONObject()
            json.put("email", tiet_email1.text)
            json.put("password", tiet_password1.text)
            json.put("device_name", "User's phone")
            val peticion = JsonObjectRequest(
                Request.Method.POST,
                getString(R.string.url_servidor) + getString(R.string.api_login),
                json,
                { response ->
                    val jsonObject = JSONObject(response.toString())
                    iniciarSesion(applicationContext, jsonObject)
                    if (validarSesion(applicationContext)) {
                        //se puede ahorrar memoria pasandolo directo
                        // val intent = Intent(this,Pantalla_principal::class.java)
                        startActivity(Intent(this, Pantalla_principal::class.java))
                        //elimina cuenta login
                        finish()
                    }
                    //Log.d(TAG, response.toString()) para ver token generado
                },
                { error ->
                    Log.e(TAG, error.toString())
                })
            if(estaEnLinea((applicationContext))){
                cola.add(peticion)
                //startActivity(intent)
            }else{
                mensajeEmergente(this,getString(R.string.error_internet))
            }

        }
    }

    fun irAregistrar() {

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

    }

    fun validarCorreo() {
        //val textEmail: String = til_email1.editText?.text?.trim().toString()
        //val textEmail2: String = tiet_email1.text?.trim().toString()


        tiet_email1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                if (editText.toString().trim().isEmpty()) {
                    til_email1.setError("El campo es requerido")
                } else {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(tiet_email1.text.toString())
                            .matches()
                    ) {
                        til_email1.isErrorEnabled = false

                    } else {
                        til_email1.error = "Error en campo correo"
                        false
                    }
//                    til_email1.setErrorEnabled(false)
//                    til_email1.setError("")
                }
            }

        })
        //val textPassword1: String = till_password1.editText?.text?.trim().toString()
        //val textPassword2: String = tiet_password1.text?.trim().toString()
    }
fun validarPassword(){
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
        //val intent = Intent(this, Pantalla_principal::class.java)
    }
    fun pruebaLibros2() {
        val cadenaCinco = """
            {
              "data": [
                {
                  "type": "books",
                  "id": "venganza-nuestra",
                  "attributes": {
                    "title": "Vengaza Nuestra",
                    "slug": "venganza-nuestra",
                    "content": "Narrativa sobre la postconquista de México",
                    "created-at": "2021-11-06T18:41:14+00:00",
                    "updated-at": "2021-11-06T18:41:14+00:00"
                  },
                  "relationships": {
                    "authors": {
                      "links": {
                        "self": "https://bookstore.test/api/v1/books/venganza-nuestra/relationships/authors",
                        "related": "https://bookstore.test/api/v1/books/venganza-nuestra/authors"
                      }
                    },
                    "categories": {
                      "links": {
                        "self": "https://bookstore.test/api/v1/books/venganza-nuestra/relationships/categories",
                        "related": "https://bookstore.test/api/v1/books/venganza-nuestra/categories"
                      }
                    }
                  },
                  "links": {
                    "self": "https://bookstore.test/api/v1/books/venganza-nuestra"
                  }
                },
                {
                  "type": "books",
                  "id": "venganza-nuestra",
                  "attributes": {
                    "title": "Vengaza Nuestra",
                    "slug": "venganza-nuestra",
                    "content": "Narrativa sobre la postconquista de México",
                    "created-at": "2021-11-06T18:41:14+00:00",
                    "updated-at": "2021-11-06T18:41:14+00:00"
                  },
                  "relationships": {
                    "authors": {
                      "links": {
                        "self": "https://bookstore.test/api/v1/books/venganza-nuestra/relationships/authors",
                        "related": "https://bookstore.test/api/v1/books/venganza-nuestra/authors"
                      }
                    },
                    "categories": {
                      "links": {
                        "self": "https://bookstore.test/api/v1/books/venganza-nuestra/relationships/categories",
                        "related": "https://bookstore.test/api/v1/books/venganza-nuestra/categories"
                      }
                    }
                  },
                  "links": {
                    "self": "https://bookstore.test/api/v1/books/venganza-nuestra"
                  }
                }
              ]
            }
        """.trimIndent()
        Log.d(TAG, "AQUI")
        val books = Json.decodeFromString<Books>(cadenaCinco)
        for (book in books.data) {
            Log.e(TAG, book.attributes.title)
            println("ayuuda")
        }
    }
}