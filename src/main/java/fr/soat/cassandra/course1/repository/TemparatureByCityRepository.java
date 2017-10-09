package fr.soat.cassandra.course1.repository;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.google.common.util.concurrent.ListenableFuture;
import fr.soat.cassandra.course1.model.TemperatureByCity;

import java.time.LocalDate;
import java.util.List;

public class TemparatureByCityRepository {

    private Session session;
    private MappingManager mappingManager;
    private Mapper<TemperatureByCity> mapper;
    private TemperatureAccessor accessor;

    public TemparatureByCityRepository(Session session) {
        this.session = session;
        this.mappingManager = new MappingManager(session);
        this.mapper = mappingManager.mapper(TemperatureByCity.class);
        this.accessor = mappingManager.createAccessor(TemperatureAccessor.class);
    }

    /* CRUD with datastax mapper */

    public void save(TemperatureByCity temperature) {
        mapper.save(temperature);
    }

    public ListenableFuture<Void> saveAsync(TemperatureByCity temperature) {
        return mapper.saveAsync(temperature);
    }

    public TemperatureByCity getById(String city, LocalDate probeDate) {
        return mapper.get(city, probeDate);
    }

    public ListenableFuture<TemperatureByCity> getByIdAsync(String city, LocalDate probeDate) {
        return mapper.getAsync(city, probeDate);
    }

    public void deleteById(String city, LocalDate probeDate) {
        mapper.delete(city, probeDate);
    }

    public void delete(TemperatureByCity temperature) {
        mapper.delete(temperature);
    }

    public ListenableFuture<Void> deleteAsync(TemperatureByCity temperature) {
        return mapper.deleteAsync(temperature);
    }

    /* cursotm queries with accessor */

    public Result<List<TemperatureByCity>> getByCity(String city) {
        return accessor.getByCity(city);
    }

    public Result<TemperatureByCity> getLastByCity(String city) {
        return accessor.getLastByCity(city);
    }

    public List<TemperatureByCity> getByCityUntil(String city, LocalDate until) {
        return accessor.getByCityUntil(city, until);
    }

    public List<TemperatureByCity> getByCityUntilAsc(String city, LocalDate until) {
        return accessor.getByCityUntilAsc(city, until);
    }

}
