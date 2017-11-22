package fr.soat.cassandra.course1.service;

import com.google.common.util.concurrent.ListenableFuture;
import fr.soat.cassandra.course1.dto.Temperature;
import fr.soat.cassandra.course1.model.TemperatureByCity;
import fr.soat.cassandra.course1.model.TemperatureByDate;
import fr.soat.cassandra.course1.repository.TemperatureByCityRepository;
import fr.soat.cassandra.course1.repository.TemperatureByDateRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TemperatureService {

    TemperatureByCityRepository temperatureByCityRepository;
    TemperatureByDateRepository temperatureByDateRepository;

    public TemperatureService(TemperatureByCityRepository temperatureByCityRepository, TemperatureByDateRepository temperatureByDateRepository) {
        this.temperatureByCityRepository = temperatureByCityRepository;
        this.temperatureByDateRepository = temperatureByDateRepository;
    }

    public void createOrUpdate(Temperature... temperatures) {
        Stream.of(temperatures).forEach(this::createOrUpdate);
    }

    // Q2.10
    public void createOrUpdate(Temperature temperature) {
        throw new RuntimeException("implement me !");
    }

    // Q2.10
    public List<Temperature> getByCity(String city) {
        throw new RuntimeException("implement me !");
    }

    // Q2.10
    public List<Temperature> getByDate(LocalDate date) {
        throw new RuntimeException("implement me !");
    }

    public static TemperatureByCity toTemperatureByCity(Temperature t) {
        throw new RuntimeException("implement me !");
    }

    public static TemperatureByDate toTemperatureBydate(Temperature t) {
        throw new RuntimeException("implement me !");
    }

    public static Temperature toDto(TemperatureByDate t) {
        throw new RuntimeException("implement me !");
    }

    public static Temperature toDto(TemperatureByCity t) {
        throw new RuntimeException("implement me !");
    }

}
