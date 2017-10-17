package fr.soat.cassandra.course1.repository;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.common.util.concurrent.ListenableFuture;
import fr.soat.cassandra.course1.model.TemperatureByDate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class TemperatureByDateRepository {

    private Session session;
    private MappingManager mappingManager;
    private Mapper<TemperatureByDate> mapper;
    private TemperatureByDateAccessor accessor;

    public TemperatureByDateRepository(Session session) {
        this.session = session;
        this.mappingManager = new MappingManager(session);
        this.mapper = mappingManager.mapper(TemperatureByDate.class);
        this.accessor = mappingManager.createAccessor(TemperatureByDateAccessor.class);
    }

    /* CRUD with datastax mapper */

    public void save(TemperatureByDate temperature) {
        mapper.save(temperature);
    }

    public ListenableFuture<Void> saveAsync(TemperatureByDate temperature) {
        return mapper.saveAsync(temperature);
    }

    public TemperatureByDate getById(String city, LocalDate date) {
        return mapper.get(city, date);
    }

    /* cursotm queries with accessor */

    public List<TemperatureByDate> getByDate(LocalDate when) {
        return accessor.getByDate(when).all();
    }

    public void save(TemperatureByDate ... temperatures) {
        Stream.of(temperatures).forEach(this::save);
    }
}
