package com.example.bookodemia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookodemia.adapter.Libro_Adapter
import com.example.bookodemia.dataclass.Books
import com.example.bookodemia.extra.eliminarSesion
import com.example.bookodemia.extra.obtenerDatosDeSesion
import com.example.bookodemia.extra.obtenerDeSesion
import com.example.bookodemia.extra.obtenerPreferencias
import com.example.bookodemia.fragmentsNavBar.Perfil_usuario
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class Pantalla_principal : AppCompatActivity() {
    private val TAG = Pantalla_principal::class.qualifiedName
    lateinit var recycler_coleccion_libros: RecyclerView

    //lateinit var btn_cerrar_sesion: Button

//    val listLibros: MutableList<Book> = mutableListOf()
//    var libroAdapter = Libro_Adapter(listLibros)
//    var parent_view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        //val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

//        val book = it.getSerializable("book") as Book
//        Log.d(TAG,book.attributes.title)
//
//        initRecyclerLibros()
        // initPantallaPrincipal()
        initRecycler()

        NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_logout -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.item2 -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
        fun obtenerDatosDeSesion(context: Context,clave:String):String{
            val sharedPreferences = obtenerPreferencias(context)
            return sharedPreferences.getString(clave,"").toString()
        }

        fun eliminarSesion(context: Context){
            val sharedPreferences = obtenerPreferencias(context)
            with(sharedPreferences.edit()){
                clear()
                apply()
            }}
    }

    fun initRecycler() {
        recycler_coleccion_libros = findViewById(R.id.recycler_coleccion_libros)
    }


    ////CON VOLLEY

//            val cola = Volley.newRequestQueue(applicationContext)
//            //object para obtener parte del objeto y sobeescribir headers (abajo)
//            val peticion = object: StringRequest(Request.Method.POST,getString(R.string.url_servidor)+getString(R.string.api_logout),null,{
//                response ->
    //Log.d(TAG,"Todo salió bien")
//                eliminarSesion(applicationContext)
//                startActivity(Intent(this,MainActivity::class.java))
//                finish()
//            },{
//                error ->
//                Log.e(TAG,error.toString())
//            }){
//                //Antes de entrar a peticion va sobreescribir este metodo
//                override fun getHeaders(): MutableMap<String, String> {
//                    val headers = HashMap<String,String>()
//                    headers["Authorization"] = "Bearer "+ obtenerDatosDeSesion(applicationContext,"token")
//                    return headers
//                }
//            }
//            cola.add(peticion)
//        }
//    }

//    private fun initRecyclerLibros(){
//        listLibros.add(Datos_Libro("Pedro Páramo","Juan Rulfo","Novela"))
//        listLibros.add(Datos_Libro("Diario de Ana Frank","Ana Frank","Autobiografía"))
//        listLibros.add(Datos_Libro("Corazón","Edmondo De Amicis","Novela"))
//        listLibros.add(Datos_Libro("El retrato de Dorian Gray","Oscar Wilde","Novela"))
//
//
//
//        recycler_coleccion_libros.layoutManager = LinearLayoutManager(this)
//        recycler_coleccion_libros.setHasFixedSize(true)
//        libroAdapter = Libro_Adapter(listLibros)
//        recycler_coleccion_libros.adapter = libroAdapter
//
//val DetallesLibrosButton: Button = findViewById(R.id.button_libros)
//
//DetallesLibrosButton.setOnClickListener {
//    val fragmentDetallesLibros = DetallesLibros()
//    val fragmentManager2: FragmentManager = supportFragmentManager
//    val transaction: FragmentTransaction = fragmentManager2.beginTransaction()
//    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//    transaction.add(android.R.id.content, fragmentDetallesLibros)
//    transaction.addToBackStack(null)
//    transaction.commit()
//}

    /*val textoNombre: String = findViewById(R.id.textView_nombre_usuario)
        textoNombre.setText("Koders")
        parent_view = requireActivity().findViewById(android.R.id.content)
        Snackbar.make(parent_view!!, "", Snackbar.LENGTH_SHORT).show()*/
    override fun onResume() {
        super.onResume()
        realizarPeticionLibros()
    }

    fun realizarPeticionLibros() {
        val cola = Volley.newRequestQueue(applicationContext)
        val peticion = object : JsonObjectRequest(
            Request.Method.GET,
            getString(R.string.url_servidor) + getString(R.string.api_libros),
            null,
            { response ->
                val books = Json.decodeFromString<Books>(response.toString())
                val adaptador = Libro_Adapter(this, books.data)
                recycler_coleccion_libros.layoutManager = LinearLayoutManager(this)
                recycler_coleccion_libros.adapter = adaptador
                adaptador.notifyDataSetChanged()
            },
            { error ->
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer ${obtenerDeSesion(applicationContext, "token")}"
                headers["Accept"] = "application/json"
                headers["Content-type"] = "application/json"
                return headers
            }
        }
        cola.add(peticion)
    }






    }
