package com.intralink.matchs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@ComponentScan(basePackages={"com.intralink.matchs.service","com.intralink.matchs.controller", "com.intralink.matchs.model",
		"com.intralink.matchs.repository", "com.intralink.matchs.configuration" })
public class CoursesRegistryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesRegistryMicroserviceApplication.class, args);
	}

}
