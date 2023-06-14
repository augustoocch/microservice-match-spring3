package com.intralink.matchs.configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

@Configuration
public class Configurations {

    @Value("${MY_DB_USERNAME}")
    private String usrname;

    @Value("${MY_DB_PASSWD}")
    private String passwd;

    @Value("${MY_DB_HOST}")
    private String host;

    @Value("${MY_DB}")
    private String db;


    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate() {
        return new R2dbcEntityTemplate(this.conennectionFactory());
    }

    @Bean
    public ConnectionFactory conennectionFactory() {

        ConnectionFactoryOptions connectionFactoryOptions = ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "mysql")
                .option(ConnectionFactoryOptions.HOST, host)
                .option(ConnectionFactoryOptions.PORT, 3306)
                .option(ConnectionFactoryOptions.USER, usrname)
                .option(ConnectionFactoryOptions.PASSWORD, passwd)
                .option(ConnectionFactoryOptions.DATABASE, db)
                .build();

        return ConnectionFactories.get(connectionFactoryOptions);
    }

}
