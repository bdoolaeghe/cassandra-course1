package fr.soat.cassandra.course1.repository;

import com.datastax.driver.core.Session;
import fr.soat.cassandra.course1.model.TemperatureByDate;
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

import static org.junit.Assert.*;

public class TemperatureByDateRepositoryTest {

    private static Session session;

    private final LocalDate today = LocalDate.now();
    private final LocalDate yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS );

    private static TemperatureByDateRepository repository;


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
        new CQLDataLoader(session).load(new ClassPathCQLDataSet("cql/create_table_temperature_by_date.cql", false));
        // instanciate service and repos
        repository = new TemperatureByDateRepository(session);
    }


    @AfterClass
    public static void shutdownEmbededCassandra() {
        if (session != null)
            session.close();
    }

    @Before
    public void setUp() throws Exception {
        session.execute("truncate table temperature_by_date");
    }

    // Q29
    @Test
    public void should_be_able_to_get_temperatures_everywhere_at_a_given_date() throws Exception {
        // Given
        TemperatureByDate temperatureParisToday = new TemperatureByDate(today, "paris", 15);
        TemperatureByDate temperatureParisYesterday = new TemperatureByDate(yesterday, "paris", 16);
        TemperatureByDate temperatureBerlinToday = new TemperatureByDate(today, "berlin", 17);
        repository.save(temperatureParisToday, temperatureBerlinToday, temperatureParisYesterday);

        // when
        List<TemperatureByDate> todayTemperatures = repository.getByDate(today);

        // Then
        assertEquals(2, todayTemperatures.size());
        assertEquals(temperatureBerlinToday, todayTemperatures.get(0));
        assertEquals(temperatureParisToday, todayTemperatures.get(1));
    }

}