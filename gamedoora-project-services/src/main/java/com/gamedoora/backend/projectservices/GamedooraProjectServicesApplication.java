package com.gamedoora.backend.projectservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gamedoora.model.*" , "com.gamedoora.backend.*"} , exclude = {DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
@EntityScan("com.gamedoora.model.dao")
public class GamedooraProjectServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamedooraProjectServicesApplication.class, args);
	}

}
