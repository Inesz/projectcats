package com.cats.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * dataSource bean is available in other java config and xml config.
 * db_security.properties is available in springSecurity.xml
 */
@Configuration
@PropertySources({
        @PropertySource(value = "classpath:properties/db.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:properties/db_security.properties", ignoreResourceNotFound = true)
})
public class dataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driverClassName"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));

        return dataSource;
    }
}

//https://www.mkyong.com/spring/spring-propertysources-example/
//https://www.javarticles.com/2016/01/spring-propertysources-annotation-example.html
/*https://www.baeldung.com/properties-with-spring
8.1. If the Properties File Is Defined in XML With <property-placeholder>
If the file is defined in the Parent context:
        @Value works in Child context: NO
        @Value works in Parent context: YES
If the file is defined in the Child context:
        @Value works in Child context: YES
        @Value works in Parent context: NO
Finally, as we discussed before, <property-placeholder> does not expose the properties to the environment, so:
        environment.getProperty works in either context: NO

8.2. If the Properties File Is Defined in Java with @PropertySource
If the file is defined in the Parent context:
        @Value works in Child context: YES
        @Value works in Parent context: YES
        environment.getProperty in Child context: YES
        environment.getProperty in Parent context: YES
If the file is defined in the Child context:
        @Value works in Child context: YES
        @Value works in Parent context: NO
        environment.getProperty in Child context: YES
        environment.getProperty in Parent context: NO*/