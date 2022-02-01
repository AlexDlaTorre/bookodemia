package com.example.bookodemia.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class Books(
    val data: MutableList<Book>
): java.io.Serializable

