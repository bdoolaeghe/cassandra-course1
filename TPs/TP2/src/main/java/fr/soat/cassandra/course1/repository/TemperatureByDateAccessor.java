package fr.soat.cassandra.course1.repository;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import fr.soat.cassandra.course1.model.TemperatureByDate;

import java.time.LocalDate;

@Accessor
public interface TemperatureByDateAccessor {

}
