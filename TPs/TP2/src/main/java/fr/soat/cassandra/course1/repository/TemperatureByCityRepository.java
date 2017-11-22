package fr.soat.cassandra.course1.repository;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.util.concurrent.ListenableFuture;
import fr.soat.cassandra.course1.model.TemperatureByCity;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TemperatureByCityRepository {

    private Session session;
    private MappingManager mappingManager;
    private Mapper<TemperatureByCity> mapper;
    private TemperatureByCityAccessor accessor;

    public TemperatureByCityRepository(Session session) {
        this.session = session;
        this.mappingManager = new MappingManager(session);
        this.mapper = mappingManager.mapper(TemperatureByCity.class);
        this.accessor = mappingManager.createAccessor(TemperatureByCityAccessor.class);
    }

    /* CRUD with datastax mapper */

    public void save(TemperatureByCity temperature) {
        mapper.save(temperature);
    }

    @VisibleForTesting
    public void save(TemperatureByCity ... temperatures) {
        Stream.of(temperatures).forEach(t -> save(t));
    }

    public ListenableFuture<Void> saveAsync(TemperatureByCity temperature) {
        return mapper.saveAsync(temperature);
    }

    // Q2.1
    public TemperatureByCity getById(String city, LocalDate date) {
        throw new RuntimeException("implement me !");
    }

    // Q2.2
    public ListenableFuture<TemperatureByCity> getByIdAsync(String city, LocalDate date) {
        throw new RuntimeException("implement me !");
    }

    public void deleteById(String city, LocalDate date) {
        mapper.delete(city, date);
    }

    /* cursotm queries with accessor */

    public List<TemperatureByCity> getByCity(String city) {
        return accessor.getByCity(city).all();
    }

    // Q2.3
    public TemperatureByCity getLastByCity(String city) {
        throw new RuntimeException("implement me !");
    }

    // Q2.4
    public ListenableFuture<TemperatureByCity> getLastByCityAsync(String city) {
        throw new RuntimeException("implement me !");
    }

    // Q2.5
    public TemperatureByCity getFirstByCity(String city) {
        throw new RuntimeException("implement me !");
    }

    // Q2.6
    public void saveBatch(TemperatureByCity ... temperatures) {
        throw new RuntimeException("implement me !");
    }

    //Q2.7
    public List<TemperatureByCity> getByCityUntil(String city, LocalDate until) {
        throw new RuntimeException("implement me !");
    }

    //Q2.8
    public List<TemperatureByCity> getByCityUntilAsc(String city, LocalDate until) {
        throw new RuntimeException("implement me !");
    }

}
