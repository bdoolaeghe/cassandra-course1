package fr.soat.cassandra.course1.repository;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.google.common.util.concurrent.ListenableFuture;
import fr.soat.cassandra.course1.model.TemperatureByCity;
import fr.soat.cassandra.course1.model.TemperatureByDate;

import java.time.LocalDate;

@Accessor
public interface TemperatureAccessor {

    // can use mapper ??
    @Query("SELECT * FROM temperature_by_city WHERE city = :city")
    Result<TemperatureByCity> getByCity(@Param("city") String city);

    // can use mapper ??
    @Query("SELECT * FROM temperature_by_city WHERE city = :city")
    ListenableFuture<Result<TemperatureByCity>> getByCityAsync(@Param("city") String city);

    // use case of accessor
    @Query("SELECT * FROM temperature_by_city WHERE city = :city limit 1")
    Result<TemperatureByCity> getLastByCity(@Param("city") String city);

    @Query("SELECT * FROM temperature_by_city WHERE city = :city and probe_date <= :before")
    Result<TemperatureByCity> getByCityUntil(@Param("city") String city, @Param("before") LocalDate before);

    @Query("SELECT * FROM temperature_by_city WHERE city = :city and probe_date <= :before order by probe_date asc")
    Result<TemperatureByCity> getByCityUntilAsc(@Param("city") String city, @Param("before") LocalDate before);

    // second table
    @Query("SELECT * FROM temperature_by_probe_date WHERE probe_date = :when;\n")
    Result<TemperatureByDate> getByDate(@Param("when") LocalDate probeDate);
}
