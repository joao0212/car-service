package br.com.car.domain.model

import java.io.Serializable

data class Car(
    val id: Long,
    val name: String,
    val model: String,
    val year: Long,
    val isEligible: Boolean? = null
) : Serializable {

    fun isEligibleToUber(): Car {
        return if (year >= 2008) {
            this.copy(isEligible = true)
        } else {
            this.copy(isEligible = false)
        }
    }
}
