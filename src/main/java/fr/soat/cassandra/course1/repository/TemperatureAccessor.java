package fr.soat.cassandra.course1.repository;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.google.common.util.concurrent.ListenableFuture;
import fr.soat.cassandra.course1.model.TemperatureByCity;
import fr.soat.cassandra.course1.model.TemperatureByDate;

import java.time.LocalDate;
import java.util.List;

@Accessor
public interface TemperatureAccessor {

    @Query("SELECT * FROM temperature_by_city WHERE city = :city")
    Result<List<TemperatureByCity>> getByCity(@Param("city") String city);

    @Query("SELECT * FROM temperature_by_city WHERE city = :city")
    ListenableFuture<List<TemperatureByCity>> getByCityAsync(@Param("city") String city);

    @Query("select * from temperature_by_city where city = :city limit 1")
    Result<TemperatureByCity> getLastByCity(@Param("city") String city);

    @Query("select * from temperature_by_city where city = :city and probe_date <= :before")
    List<TemperatureByCity> getByCityUntil(@Param("city") String city, LocalDate before);

    @Query("select * from temperature_by_city where city = :city and probe_date <= :before order by probe_date asc")
    List<TemperatureByCity> getByCityUntilAsc(@Param("city") String city, LocalDate before);

    @Query("SELECT * FROM temperature_by_probe_date where probe_date = :when;\n")
    List<TemperatureByDate> getByDate(@Param("when") LocalDate probeDate);
}
