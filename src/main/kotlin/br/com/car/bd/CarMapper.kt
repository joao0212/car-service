package br.com.car.bd

import br.com.car.Car
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class CarMapper : RowMapper<Car> {

    override fun mapRow(rs: ResultSet, rowNum: Int) = Car(
        id = requireNotNull(rs.getLong("id")),
        name = requireNotNull(rs.getString("name")),
        model = requireNotNull(rs.getString("model"))
    )
}