package br.com.car.adapters.bd

import br.com.car.domain.model.Car
import br.com.car.domain.ports.CarRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CarRepository(
    private val jdbcTemplate: JdbcTemplate
): CarRepository {

    override fun listAll(): List<Car> = jdbcTemplate.query("SELECT * FROM car", CarMapper())

    override fun listByModel(model: String): List<Car> =
        jdbcTemplate.query("SELECT * FROM car WHERE model = ?", CarMapper(), model)

    override fun save(car: Car) = jdbcTemplate.update(
        "INSERT INTO car(name, model) VALUES(?,?)", car.name, car.model)

    override fun update(car: Car, id: Long) = jdbcTemplate.update(
        "UPDATE car SET name = ?, model = ? WHERE id = ?", car.name, car.model, id
    )

    override fun findById(id: Long): Car? = jdbcTemplate.queryForObject("SELECT * FROM car WHERE id = ?", CarMapper(), id)
}
