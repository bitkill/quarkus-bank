package org.ruifernandes.bankjava.containers;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class PostgresContainer implements QuarkusTestResourceLifecycleManager {

    private static final String PG_DATABASE = "bankjava";
    private static final String PG_USERNAME = "bankjava";
    private static final String PG_PASSWORD = "secret";


    private final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres")
        .withDatabaseName(PG_DATABASE)
        .withUsername(PG_USERNAME)
        .withPassword(PG_PASSWORD);

    @Override
    public Map<String, String> start() {
        postgres.start();

        var host = postgres.getHost();
        var port = postgres.getFirstMappedPort();

        return Map.of(
            "quarkus.datasource.db-kind", "postgresql",
            "quarkus.datasource.username", PG_USERNAME,
            "quarkus.datasource.password", PG_PASSWORD,
            "quarkus.datasource.jdbc.url",
            String.format("jdbc:postgresql://%s:%s/%s?currentSchema=public", host, port, PG_DATABASE)
        );
    }

    @Override
    public void stop() {
        postgres.stop();
    }
}
