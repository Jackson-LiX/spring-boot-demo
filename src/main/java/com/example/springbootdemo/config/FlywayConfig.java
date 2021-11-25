package com.example.springbootdemo.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * The flyway config
 *
 * @author Jackson
 */
@Configuration
public class FlywayConfig {

    @Bean
    public CommandLineRunner flyway(DataSource dataSource) {
        return param -> {
            Flyway flyway = Flyway.configure()
                .schemas("demo")
                .dataSource(dataSource)
                .locations("migrations/schema")
                .table("schema_version")
                .baselineOnMigrate(true).load();
        flyway.migrate();
        // The commented code is used to execute the repeatable SQL
////        Flyway flywayRepeatable = Flyway.configure()
////                .schemas("demo")
////                .dataSource(dataSource)
////                .locations("migrations/function")
////                .table("function_version")
////                .baselineOnMigrate(true).load();
////        flywayRepeatable.migrate();
        };
    }
}
