package fr.soat.cassandra.course1.repository;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.google.common.util.concurrent.ListenableFuture;
import fr.soat.cassandra.course1.model.TemperatureByCity;

import java.time.LocalDate;

@Accessor
public interface TemperatureByCityAccessor {

    @Query("SELECT * FROM temperature_by_city WHERE city = :city")
    Result<TemperatureByCity> getByCity(@Param("city") String city);

    @Query("SELECT * FROM temperature_by_city WHERE city = :city")
    ListenableFuture<Result<TemperatureByCity>> getByCityAsync(@Param("city") String city);

    // use case of accessor
    // ...

}
