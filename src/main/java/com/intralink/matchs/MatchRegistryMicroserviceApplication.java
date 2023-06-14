package com.intralink.matchs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableR2dbcRepositories
@SpringBootApplication(exclude = R2dbcDataAutoConfiguration.class)
@ComponentScan(basePackages={"com.intralink.matchs.configuration","com.intralink.matchs.service","com.intralink.matchs.controller", "com.intralink.matchs.model",
		"com.intralink.matchs.repository" })
public class MatchRegistryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchRegistryMicroserviceApplication.class, args);
	}



}
