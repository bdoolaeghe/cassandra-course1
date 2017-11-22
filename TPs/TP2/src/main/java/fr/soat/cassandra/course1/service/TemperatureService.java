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
        // save in //
        ListenableFuture<Void> futureByCity = temperatureByCityRepository.saveAsync(toTemperatureByCity(temperature));
        ListenableFuture<Void> futureByDate = temperatureByDateRepository.saveAsync(toTemperatureBydate(temperature));

        // wait for termination
        try {
            futureByCity.get();
            futureByDate.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("fail to save temperature " + temperature, e);
        }
    }

    // Q2.10
    public List<Temperature> getByCity(String city) {
        return temperatureByCityRepository.getByCity(city)
                .stream()
                .map(TemperatureService::toDto)
                .collect(toList());
    }

    // Q2.10
    public List<Temperature> getByDate(LocalDate date) {
        return temperatureByDateRepository.getByDate(date)
                .stream()
                .map(TemperatureService::toDto)
                .collect(toList());
    }

    public static TemperatureByCity toTemperatureByCity(Temperature t) {
        return (t == null) ? null : new TemperatureByCity(t.getCity(), t.getDate(), t.getTemperature());
    }

    public static TemperatureByDate toTemperatureBydate(Temperature t) {
        return (t == null) ? null : new TemperatureByDate(t.getDate(), t.getCity(), t.getTemperature());
    }

    public static Temperature toDto(TemperatureByDate t) {
        return (t == null) ? null : new Temperature(t.getCity(), t.getDate(), t.getTemperature());
    }

    public static Temperature toDto(TemperatureByCity t) {
        return (t == null) ? null : new Temperature(t.getCity(), t.getDate(), t.getTemperature());
    }

}
