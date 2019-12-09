package com.cats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class dbInitConfig {

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(resourceDatabasePopulator());
        return initializer;
    }

    private ResourceDatabasePopulator resourceDatabasePopulator() {
        ResourceDatabasePopulator dbp = new ResourceDatabasePopulator();
        dbp.setSqlScriptEncoding("UTF-8");
        dbp.addScripts(
                new ClassPathResource("database\\schema-DDL-mysql.sql"),
                new ClassPathResource("database\\schema-DML-mysql.sql"));
        dbp.setContinueOnError(true);

        return dbp;
    }
}