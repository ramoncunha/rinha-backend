package com.rinha.backend.infrastructure.config;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import javax.sql.DataSource;

@Factory
public class JOOQConfig {

    @Bean
    public Configuration getConfiguration(DataSource dataSource) {
        DefaultConfiguration defaultConfiguration = new DefaultConfiguration();
        defaultConfiguration.setSQLDialect(SQLDialect.POSTGRES);
        defaultConfiguration.setDataSource(dataSource);
        return defaultConfiguration;
    }
}
