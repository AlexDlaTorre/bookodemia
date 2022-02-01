package com.example.bookodemia.dataclassempleados

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Empleado(
    val nombre:String,
    val telefono:Long,
    val casado:Boolean,
    //para renombrar y poner el nombre correcto en serialName se pone como esta en el objeto como lo debe esperar recibir
    @SerialName("cuota_hora")
    //aquí se usa la variable de acuerdo a como debería venir por buenas prácticas
    val cuotaHora:Double,
    @SerialName("dias-De-Semana-Trabajo")
    val diasDeSemanaTrabajo:List<String>,
    val observacion: List<Observacion>
)
