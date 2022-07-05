package br.com.car

import java.io.Serializable

data class Car(
    val id: Long,
    val name: String,
    val model: String
) : Serializable