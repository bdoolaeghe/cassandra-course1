package fr.soat.cassandra.course1.repository;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import fr.soat.cassandra.course1.model.TemperatureByDate;

import java.time.LocalDate;

@Accessor
public interface TemperatureByDateAccessor {

    // Q29
    @Query("SELECT * FROM temperature_by_date WHERE date = :when;\n")
    Result<TemperatureByDate> getByDate(@Param("when") LocalDate date);
}
