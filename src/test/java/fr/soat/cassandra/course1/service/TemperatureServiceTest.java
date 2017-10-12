package fr.soat.cassandra.course1.service;

import fr.soat.cassandra.course1.repository.TemparatureByCityRepository;
import fr.soat.cassandra.course1.repository.TemparatureByDateRepository;
import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.CassandraCQLUnit;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureServiceTest {

    @Rule
    public CassandraCQLUnit cassandraCQLUnit = new CassandraCQLUnit(new ClassPathCQLDataSet("cql/create_table_temperature_by_city.cql","my_keyspace"));

    private final TemparatureByCityRepository temparatureByCityRepository = new TemparatureByCityRepository(cassandraCQLUnit.session);
    private final TemparatureByDateRepository temparatureByDateRepository = new TemparatureByDateRepository(cassandraCQLUnit.session);
    private final TemperatureService temperatureService = new TemperatureService(temparatureByCityRepository, temparatureByDateRepository);

    @Before
    public void setUp() throws Exception {
        new CQLDataLoader(cassandraCQLUnit.session).load(new ClassPathCQLDataSet("cql/insert_dataset.cql"));;
    }

    @Test
    public void should_be_able_to_save_a_temperature_in_a_given_city_and_date() throws Exception {
    }

    @Test
    public void should_be_able_to_get_temperatures_in_a_city() throws Exception {
    }

    @Test
    public void should_be_able_to_delete_one_temperature() throws Exception {
    }

    // Q21
    @Test
    public void should_be_able_to_get_a_temperature_in_a_city_at_a_given_date() throws Exception {
    }

    // Q22
    @Test
    public void should_be_able_to_async_get_a_temperature_in_a_city_at_a_given_date() throws Exception {
    }

    // Q25
    @Test
    public void should_be_able_to_bulk_save_temperatures() throws Exception {
    }

    // Q26
    @Test
    public void should_be_able_to_get_temperatures_everywhere_at_a_given_date() throws Exception {
    }

    // Q23
    @Test
    public void should_be_able_to_get_last_temperature_in_a_city() throws Exception {
    }

    // Q24
    @Test
    public void should_be_able_to_async_get_last_temperature_in_a_city() throws Exception {
    }

    // Q27
    @Test
    public void should_be_able_to_get_temperatures_in_a_city_until_a_bound_date() throws Exception {
    }

    // Q28
    @Test
    public void should_be_able_to_get_temperatures_in_a_city_until_a_bound_date_in_chronologic_reverse_order() throws Exception {
    }

}