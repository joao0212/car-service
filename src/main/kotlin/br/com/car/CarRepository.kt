package br.com.car

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CarRepository(
    private val jdbcTemplate: JdbcTemplate
) {

    fun listAll(): List<Car> = jdbcTemplate.query("SELECT * FROM car", CarMapper())

    fun listByModel(model: String): List<Car> =
        jdbcTemplate.query("SELECT * FROM car WHERE model = ?", CarMapper(), model)

    fun save(car: Car) = jdbcTemplate.update(
        "INSERT INTO car(name, model) VALUES(?,?)", car.name, car.model)

    fun update(car: Car, id: Long) = jdbcTemplate.update(
        "UPDATE car SET name = ?, model = ? WHERE id = ?", car.name, car.model, id
    )
}
