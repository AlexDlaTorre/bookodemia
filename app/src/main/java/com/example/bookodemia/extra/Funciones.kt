package com.example.bookodemia.extra

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.bookodemia.R
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject

fun obtenerPreferencias(context: Context): SharedPreferences {
    //se crea shared preferences encriptadas, el primer parametro que pide es nombre archivo
    //el nombre del archivo debe de ir a strings
    return EncryptedSharedPreferences.create(
        context.getString(
            //Android genera llave maestra unica p cada dispositivo p encriptar y desencriptar
            R.string.name_file_preferences
        ),
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

//pasar valores necesario p iniciar sesion
fun iniciarSesion(context: Context, jsonObject: JSONObject) {
    val sharedPreferences = obtenerPreferencias(context)
    //se agrega como string a partir del json
    with(sharedPreferences.edit()) {
        putString("token", jsonObject.getString(context.getString(R.string.key_token)))
        //p guardar c sharedPreferences se usa "token", p obtenerlo c jason se usa key token
        //obtiene el plain text token(guardar como string)y lo guarda en token
        //putString("token", jsonObject["token"].toString())
        //jsonObject en la posición de token
        //genera hilo externo que guarda c uno de los campos
        //con commit(), es lo mismo pero no genera hilo externo y app se queda parada un momento
        apply()
    }
}

fun obtenerDeSesion(context: Context, clave: String): String {
    val sharedPreferences = obtenerPreferencias(context)
    return sharedPreferences.getString(clave, "").toString()
}

fun validarSesion(context: Context): Boolean {
    val sharedPreferences = obtenerPreferencias(context)
    //si no está dado de alta token, dar cadena vacía
    val token = sharedPreferences.getString("token", "")
    return !token.isNullOrEmpty()

}

//+++++Para cerrar sesion+++

fun obtenerDatosDeSesion(context: Context, clave: String): String {
    val sharedPreferences = obtenerPreferencias(context)
    return sharedPreferences.getString(clave, "").toString()
}

fun eliminarSesion(context: Context) {
    val sharedPreferences = obtenerPreferencias(context)
    with(sharedPreferences.edit()) {
        clear()
        apply()
    }
}

fun estaEnLinea(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_TRANSPORT_ETHERNET")
            return true
        }
    }
    return false
}

fun mensajeEmergente( activity: Activity, mensaje:String){
    val snackbar = Snackbar.make(activity.findViewById(android.R.id.content),mensaje,Snackbar.LENGTH_LONG)
    val view = snackbar.view
    val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
    snackbar.show()
}
