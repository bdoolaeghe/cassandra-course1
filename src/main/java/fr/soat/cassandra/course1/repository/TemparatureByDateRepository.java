package fr.soat.cassandra.course1.repository;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.common.util.concurrent.ListenableFuture;
import fr.soat.cassandra.course1.model.TemperatureByDate;

import java.time.LocalDate;
import java.util.List;

public class TemparatureByDateRepository {

    private Session session;
    private MappingManager mappingManager;
    private Mapper<TemperatureByDate> mapper;
    private TemperatureAccessor accessor;

    public TemparatureByDateRepository(Session session) {
        this.session = session;
        this.mappingManager = new MappingManager(session);
        this.mapper = mappingManager.mapper(TemperatureByDate.class);
        this.accessor = mappingManager.createAccessor(TemperatureAccessor.class);
    }

    /* CRUD with datastax mapper */

    public void save(TemperatureByDate temperature) {
        mapper.save(temperature);
    }

    public ListenableFuture<Void> saveAsync(TemperatureByDate temperature) {
        return mapper.saveAsync(temperature);
    }

    public TemperatureByDate getById(String city, LocalDate probeDate) {
        return mapper.get(city, probeDate);
    }

    public ListenableFuture<TemperatureByDate> getByIdAsync(String city, LocalDate probeDate) {
        return mapper.getAsync(city, probeDate);
    }

    public void deleteById(String city, LocalDate probeDate) {
        mapper.delete(city, probeDate);
    }

    public void delete(TemperatureByDate temperature) {
        mapper.delete(temperature);
    }

    public ListenableFuture<Void> deleteAsync(TemperatureByDate temperature) {
        return mapper.deleteAsync(temperature);
    }

    /* cursotm queries with accessor */

    public List<TemperatureByDate> getByDate(LocalDate when) {
        return accessor.getByDate(when).all();
    }


}
