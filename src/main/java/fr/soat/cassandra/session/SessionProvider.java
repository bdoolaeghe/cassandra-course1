package fr.soat.cassandra.session;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.extras.codecs.jdk8.LocalDateCodec;
import lombok.Getter;

public class SessionProvider {

    public static final String KEYSPACE = "my_keyspace";

    @Getter
    private int port;

    private Cluster cluster;

    public SessionProvider() {
        // dfault cassandra port
        this(9042);
    }

    public SessionProvider(int port) {
        this.port = port;
        init(port);
    }

    private void init(int port) {
        Cluster.Builder clusterBuilder = Cluster.builder()
                .addContactPoints("localhost")
                .withPort(port);
        cluster = clusterBuilder.build();
        // register type codecs
        cluster.getConfiguration().getCodecRegistry()
                .register(LocalDateCodec.instance);
    }

    public Session newSession() {
        return cluster.connect();
    }

    public Session newSession(String keyspace) {
        return cluster.connect(keyspace);
    }

}
