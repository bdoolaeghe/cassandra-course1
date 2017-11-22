package fr.soat.cassandra.course1.service;

import com.datastax.driver.core.Session;
import fr.soat.cassandra.course1.dto.Temperature;
import fr.soat.cassandra.course1.repository.TemperatureByCityRepository;
import fr.soat.cassandra.course1.repository.TemperatureByDateRepository;
import fr.soat.cassandra.session.SessionProvider;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TemperatureServiceTest {

    private static Session session;

    private final LocalDate today = LocalDate.now();
    private final LocalDate yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS );
    private final LocalDate beforeYesterday = LocalDate.now().minus(2, ChronoUnit.DAYS );

    private static TemperatureByCityRepository temperatureByCityRepository;
    private static TemperatureByDateRepository temperatureByDateRepository;
    private static TemperatureService temperatureService;


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
        new CQLDataLoader(session).load(new ClassPathCQLDataSet("cql/create_table_temperature_by_date.cql", false));
        // instanciate service and repos
        temperatureByCityRepository = new TemperatureByCityRepository(session);
        temperatureByDateRepository = new TemperatureByDateRepository(session);
        temperatureService = new TemperatureService(temperatureByCityRepository, temperatureByDateRepository);
    }


    @AfterClass
    public static void shutdownEmbededCassandra() {
        if (session != null)
            session.close();
    }

    @Before
    public void setUp() throws Exception {
        session.execute("truncate table temperature_by_city");
        session.execute("truncate table temperature_by_date");
    }

    // Q2.10
    @Test
    public void should_be_able_to_save_and_reload_temperatures_in_a_city() throws Exception {
        // Given
        Temperature temperatureParisToday = new Temperature("paris", today, 15);
        Temperature temperatureParisYesterday = new Temperature("paris", yesterday, 16);
        Temperature temperatureBerlinToday = new Temperature("berlin", today, 17);
        Temperature temperatureBerlinYesterday = new Temperature("berlin", yesterday, 14);
        temperatureService.createOrUpdate(temperatureParisToday, temperatureBerlinToday, temperatureParisYesterday, temperatureBerlinYesterday);

        // When
        List<Temperature> temperaturesInParis = temperatureService.getByCity("paris");

        // Then
        assertEquals(2, temperaturesInParis.size());
        assertEquals(temperatureParisToday, temperaturesInParis.get(0));
        assertEquals(temperatureParisYesterday, temperaturesInParis.get(1));
    }

    // Q2.10
    @Test
    public void should_be_able_to_save_and_reload_temperatures_at_a_given_date() throws Exception {
        // Given
        Temperature temperatureParisToday = new Temperature("paris", today, 15);
        Temperature temperatureParisYesterday = new Temperature("paris", yesterday, 16);
        Temperature temperatureBerlinToday = new Temperature("berlin", today, 17);
        Temperature temperatureBerlinYesterday = new Temperature("berlin", yesterday, 14);
        temperatureService.createOrUpdate(temperatureParisToday, temperatureBerlinToday, temperatureParisYesterday, temperatureBerlinYesterday);

        // When
        List<Temperature> temperaturesToday = temperatureService.getByDate(today);

        // Then
        assertEquals(2, temperaturesToday.size());
        assertEquals(temperatureBerlinToday, temperaturesToday.get(0));
        assertEquals(temperatureParisToday, temperaturesToday.get(1));
    }



}