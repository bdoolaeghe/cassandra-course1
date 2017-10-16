package fr.soat.cassandra.course1.service;

import com.datastax.driver.core.Session;
import fr.soat.cassandra.course1.dto.Temperature;
import fr.soat.cassandra.course1.repository.TemparatureByCityRepository;
import fr.soat.cassandra.course1.repository.TemparatureByDateRepository;
import fr.soat.cassandra.session.SessionProvider;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.CassandraCQLUnit;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.*;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TemperatureServiceTest {

    private static Session session;

    private final LocalDate today = LocalDate.now();

    private TemparatureByCityRepository temparatureByCityRepository;
    private TemparatureByDateRepository temparatureByDateRepository;
    private TemperatureService temperatureService;


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
        new CQLDataLoader(session).load(new ClassPathCQLDataSet("cql/create_table_temperature_by_probe_date.cql", false));
    }

    @AfterClass
    public static void shutdownEmbededCassandra() {
        if (session != null)
            session.close();
    }

    @Before
    public void setUp() throws Exception {
        temparatureByCityRepository = new TemparatureByCityRepository(session);
        temparatureByDateRepository = new TemparatureByDateRepository(session);
        temperatureService = new TemperatureService(temparatureByCityRepository, temparatureByDateRepository);

        new CQLDataLoader(session).load(new ClassPathCQLDataSet("cql/insert_dataset.cql", false));
    }

    @Test
    public void should_be_able_to_save_a_temperature_in_a_given_city_and_date() throws Exception {
//        // Given
//        Temperature temperature = new Temperature("paris", today, 16);
//        // When
//        temperatureService.createOrUpdate(temperature);
//        // Then
//        Temperature reloadedTemperature = temperatureService.get("paris", today);
//        assertEquals(temperature, reloadedTemperature);
    }

    @Test
    public void should_be_able_to_get_temperatures_in_a_city() throws Exception {
        // Given
        Temperature temperature = new Temperature("paris", today, 16);
        // When
        temperatureService.createOrUpdate(temperature);
        // Then
        Temperature reloadedTemperature = temperatureService.get("paris", today);
        assertEquals(temperature, reloadedTemperature);
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