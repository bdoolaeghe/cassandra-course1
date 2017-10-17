package fr.soat.cassandra.course1.service;

import com.google.common.util.concurrent.ListenableFuture;
import fr.soat.cassandra.course1.dto.Temperature;
import fr.soat.cassandra.course1.model.TemperatureByCity;
import fr.soat.cassandra.course1.model.TemperatureByDate;
import fr.soat.cassandra.course1.repository.TemparatureByCityRepository;
import fr.soat.cassandra.course1.repository.TemparatureByDateRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TemperatureService {

    TemparatureByCityRepository temparatureByCityRepository;
    TemparatureByDateRepository temparatureByDateRepository;

    public TemperatureService(TemparatureByCityRepository temparatureByCityRepository, TemparatureByDateRepository temparatureByDateRepository) {
        this.temparatureByCityRepository = temparatureByCityRepository;
        this.temparatureByDateRepository = temparatureByDateRepository;
    }

    public void createOrUpdate(List<Temperature> temperatures) {
        throw new RuntimeException("implement me !");
    }

    public void createOrUpdate(Temperature temperatures) {
        // save in //
        ListenableFuture<Void> futureByCity = temparatureByCityRepository.saveAsync(toTemperatureByCity(temperatures));
        ListenableFuture<Void> futureByProbeDate = temparatureByDateRepository.saveAsync(toTemperatureByProbeDate(temperatures));
        // wait to resynchonize
        try {
            futureByCity.get();
            futureByProbeDate.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to create or update " + temperatures, e);
        }
    }

    public static TemperatureByCity toTemperatureByCity(Temperature temperatures) {
        return new TemperatureByCity(temperatures.getCity(), temperatures.getProbeDate(), temperatures.getTemperature());
    }

    public static TemperatureByDate toTemperatureByProbeDate(Temperature temperatures) {
        return new TemperatureByDate(temperatures.getProbeDate(), temperatures.getCity(), temperatures.getTemperature());
    }

    public static Temperature toDto(TemperatureByDate t) {
        return new Temperature(t.getCity(), t.getProbeDate(), t.getTemperature());
    }

    public static Temperature toDto(TemperatureByCity t) {
        return new Temperature(t.getCity(), t.getProbeDate(), t.getTemperature());
    }

    public void delete(String city, LocalDate when) {
        throw new RuntimeException("implement me !");
    }

    public Temperature get(String city, LocalDate when) {
        return toDto(temparatureByCityRepository.getById(city, when));
    }

    public List<Temperature> get(LocalDate when) {
        throw new RuntimeException("implement me !");
    }

    public List<Temperature> get(String city) {
        throw new RuntimeException("implement me !");
    }

    public Temperature getLast(String city) {
        throw new RuntimeException("implement me !");
    }

    public enum Order {
        ASC,
        DESC
    }

    public List<Temperature> getUntill(String city, LocalDate until, Order order) {
        throw new RuntimeException("implement me !");
    }

}
