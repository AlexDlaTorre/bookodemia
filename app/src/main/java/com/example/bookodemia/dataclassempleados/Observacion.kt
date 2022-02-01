package com.example.bookodemia.dataclassempleados

import kotlinx.serialization.Serializable

@Serializable
data class Observacion(
    val fecha:String,
    val comentarios:String
)
