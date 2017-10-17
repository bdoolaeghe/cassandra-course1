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
    @Query("SELECT * FROM temperature_by_city WHERE city = :city limit 1")
    TemperatureByCity getLastByCity(@Param("city") String city);

    @Query("SELECT * FROM temperature_by_city WHERE city = :city limit 1")
    ListenableFuture<TemperatureByCity> getLastByCityAsync(@Param("city") String city);

    @Query("SELECT * FROM temperature_by_city WHERE city = :city and date <= :before")
    Result<TemperatureByCity> getByCityUntil(@Param("city") String city, @Param("before") LocalDate before);

    @Query("SELECT * FROM temperature_by_city WHERE city = :city and date <= :before order by date asc")
    Result<TemperatureByCity> getByCityUntilAsc(@Param("city") String city, @Param("before") LocalDate before);

    //Q25
    @Query("SELECT * FROM temperature_by_city WHERE city = :city ORDER BY date ASC LIMIT 1")
    TemperatureByCity getFirstByCity(@Param("city") String city);

}
