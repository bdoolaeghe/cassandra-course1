package fr.soat.cassandra.course1.service;

import fr.soat.cassandra.course1.dto.Temperature;
import fr.soat.cassandra.course1.repository.TemparatureByCityRepository;
import fr.soat.cassandra.course1.repository.TemparatureByDateRepository;

import java.time.LocalDate;
import java.util.List;

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
        throw new RuntimeException("implement me !");
    }

    public void delete(String city, LocalDate when) {
        throw new RuntimeException("implement me !");
    }

    public Temperature get(String city, LocalDate when) {
        throw new RuntimeException("implement me !");
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
