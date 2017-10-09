package fr.soat.cassandra.session;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class SessionProvider {

    private static final String keyspaceName = "my_keyspace" ;
    private Session session;

    private SessionProvider() {
        init(this);
    }

    private void init(SessionProvider sessionProvider) {
        Cluster.Builder clusterBuilder = Cluster.builder()
                .addContactPoints("localhost");
        Cluster cluster = clusterBuilder.build();
        this.session = cluster.connect(keyspaceName);
    }

    private static SessionProvider INSTANCE = new SessionProvider();

    public SessionProvider getInstance() {
        return INSTANCE;
    }

}
