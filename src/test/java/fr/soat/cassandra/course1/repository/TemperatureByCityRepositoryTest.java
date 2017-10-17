package fr.soat.cassandra.course1.repository;

import com.datastax.driver.core.Session;
import com.google.common.util.concurrent.ListenableFuture;
import fr.soat.cassandra.course1.model.TemperatureByCity;
import fr.soat.cassandra.session.SessionProvider;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TemperatureByCityRepositoryTest {

    private static Session session;

    private final LocalDate today = LocalDate.now();
    private final LocalDate yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS );
    private final LocalDate beforeYesterday = LocalDate.now().minus(2, ChronoUnit.DAYS );

    private static TemperatureByCityRepository repository;


    @BeforeClass
    public static void startup() throws InterruptedException, IOException, TTransportException {
        // startup embeded cassandra
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        // create session
        SessionProvider sessionProvider = new SessionProvider(9142);
        Session initSession = sessionProvider.newSession();
        // create keyspace
        new CQLDataLoader(initSession).load(new ClassPathCQLDataSet("cql/create_keyspace.cql"));
        initSession.close();
        // connect to my_keyspace
        session = sessionProvider.newSession(SessionProvider.KEYSPACE);
        // create tables
        new CQLDataLoader(session).load(new ClassPathCQLDataSet("cql/create_table_temperature_by_city.cql", false));
        // instanciate service and repos
        repository = new TemperatureByCityRepository(session);
    }


    @AfterClass
    public static void shutdownEmbededCassandra() {
        if (session != null)
            session.close();
    }

    @Before
    public void setUp() throws Exception {
        session.execute("truncate table temperature_by_city");
    }

    @Test
    public void should_be_able_to_save_and_reload_temperatures_in_a_city() throws Exception {
        // Given
        TemperatureByCity temperature = new TemperatureByCity("paris", today, 16);
        // When
        repository.save(temperature);
        // Then
        TemperatureByCity reloadedTemperature = repository.getById("paris", today);
        assertEquals(temperature, reloadedTemperature);
    }

    @Test
    public void should_be_able_to_delete_one_temperature() throws Exception {
        // Given
        TemperatureByCity temperature = new TemperatureByCity("paris", today, 16);
        repository.save(temperature);

        // When
        repository.deleteById("paris", today);

        // Then
        TemperatureByCity reloadedTemperature = repository.getById("paris", today);
        assertNull("expected " + reloadedTemperature + " to have been deleted", reloadedTemperature);
    }

    // Q21
    @Test
    public void should_be_able_to_get_a_temperature_in_a_city_at_a_given_date() throws Exception {
        // Given
        TemperatureByCity temperatureBeforeYesterday = new TemperatureByCity("paris", beforeYesterday, 16);
        TemperatureByCity temperatureYesterday = new TemperatureByCity("paris", yesterday, 16);
        TemperatureByCity temperatureToday = new TemperatureByCity("paris", today, 16);
        repository.save(temperatureBeforeYesterday, temperatureYesterday, temperatureToday);

        // When
        TemperatureByCity reloadedTemperature = repository.getById("paris", yesterday);

        // Then
        assertEquals(temperatureYesterday, reloadedTemperature);
    }

    // Q22
    @Test
    public void should_be_able_to_async_get_a_temperature_in_a_city_at_a_given_date() throws Exception {
        // Given
        TemperatureByCity temperatureBeforeYesterday = new TemperatureByCity("paris", beforeYesterday, 16);
        TemperatureByCity temperatureYesterday = new TemperatureByCity("paris", yesterday, 16);
        TemperatureByCity temperatureToday = new TemperatureByCity("paris", today, 16);
        repository.save(temperatureBeforeYesterday, temperatureYesterday, temperatureToday);

        // When
        ListenableFuture<TemperatureByCity> reloadedTemperature = repository.getByIdAsync("paris", yesterday);

        // Then
        assertEquals(temperatureYesterday, reloadedTemperature.get());
    }


    // Q23
    @Test
    public void should_be_able_to_get_last_temperature_in_a_city() throws Exception {
        // Given
        TemperatureByCity temperatureBeforeYesterday = new TemperatureByCity("paris", beforeYesterday, 16);
        TemperatureByCity temperatureYesterday = new TemperatureByCity("paris", yesterday, 16);
        TemperatureByCity temperatureToday = new TemperatureByCity("paris", today, 16);
        repository.save(temperatureBeforeYesterday, temperatureYesterday, temperatureToday);

        // When
        TemperatureByCity lastTemperature = repository.getLastByCity("paris");

        // Then
        assertEquals(temperatureToday, lastTemperature);
    }

    // Q24
    @Test
    public void should_be_able_to_async_get_last_temperature_in_a_city() throws Exception {
        // Given
        TemperatureByCity temperatureBeforeYesterday = new TemperatureByCity("paris", beforeYesterday, 16);
        TemperatureByCity temperatureYesterday = new TemperatureByCity("paris", yesterday, 16);
        TemperatureByCity temperatureToday = new TemperatureByCity("paris", today, 16);
        repository.save(temperatureBeforeYesterday, temperatureYesterday, temperatureToday);

        // When
        ListenableFuture<TemperatureByCity> lastTemperature = repository.getLastByCityAsync("paris");

        // Then
        assertEquals(temperatureToday, lastTemperature.get());
    }

    // Q25
    @Test
    public void should_be_able_to_get_first_temperature_in_a_city() throws Exception {
        // Given
        TemperatureByCity temperatureBeforeYesterday = new TemperatureByCity("paris", beforeYesterday, 16);
        TemperatureByCity temperatureYesterday = new TemperatureByCity("paris", yesterday, 16);
        TemperatureByCity temperatureToday = new TemperatureByCity("paris", today, 16);
        repository.save(temperatureBeforeYesterday, temperatureYesterday, temperatureToday);

        // When
        TemperatureByCity firstTemperature = repository.getFirstByCity("paris");

        // Then
        assertEquals(temperatureBeforeYesterday, firstTemperature);
    }


    // Q26
    @Test
    public void should_be_able_to_bulk_save_temperatures() throws Exception {
        // Given
        TemperatureByCity temperatureParisToday = new TemperatureByCity("paris", today, 15);
        TemperatureByCity temperatureParisYesterday = new TemperatureByCity("paris", yesterday, 16);
        TemperatureByCity temperatureBerlinToday = new TemperatureByCity("berlin", today, 17);

        // when
        repository.saveBatch(temperatureParisToday, temperatureBerlinToday, temperatureParisYesterday);

        // Then
        assertThat(repository.getById("paris", today), Is.is(temperatureParisToday));
        assertThat(repository.getById("paris", yesterday), Is.is(temperatureParisYesterday));
        assertThat(repository.getById("berlin", today), Is.is(temperatureBerlinToday));
    }


    // Q27
    @Test
    public void should_be_able_to_get_temperatures_in_a_city_until_a_bound_date() throws Exception {
        // Given
        TemperatureByCity temperatureBeforeYesterday = new TemperatureByCity("paris", beforeYesterday, 16);
        TemperatureByCity temperatureYesterday = new TemperatureByCity("paris", yesterday, 16);
        TemperatureByCity temperatureToday = new TemperatureByCity("paris", today, 16);
        repository.save(temperatureBeforeYesterday, temperatureYesterday, temperatureToday);

        // When
        List<TemperatureByCity> lastTemperaturesTillYesterday = repository.getByCityUntil("paris", yesterday);

        // Then
        assertEquals(2, lastTemperaturesTillYesterday.size());
        assertEquals(temperatureYesterday, lastTemperaturesTillYesterday.get(0));
        assertEquals(temperatureBeforeYesterday, lastTemperaturesTillYesterday.get(1));
    }

    // Q28
    @Test
    public void should_be_able_to_get_temperatures_in_a_city_until_a_bound_date_in_chronologic_reverse_order() throws Exception {
        // Given
        TemperatureByCity temperatureBeforeYesterday = new TemperatureByCity("paris", beforeYesterday, 16);
        TemperatureByCity temperatureYesterday = new TemperatureByCity("paris", yesterday, 16);
        TemperatureByCity temperatureToday = new TemperatureByCity("paris", today, 16);
        repository.save(temperatureBeforeYesterday, temperatureYesterday, temperatureToday);

        // When
        List<TemperatureByCity> lastTemperaturesTillYesterday = repository.getByCityUntilAsc("paris", yesterday);

        // Then
        assertEquals(2, lastTemperaturesTillYesterday.size());
        assertEquals(temperatureBeforeYesterday, lastTemperaturesTillYesterday.get(0));
        assertEquals(temperatureYesterday, lastTemperaturesTillYesterday.get(1));
    }


}