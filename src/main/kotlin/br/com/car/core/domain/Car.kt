package br.com.car.core.domain

import java.io.Serializable

data class Car(
    val id: Long,
    val name: String,
    val model: String
) : Serializable