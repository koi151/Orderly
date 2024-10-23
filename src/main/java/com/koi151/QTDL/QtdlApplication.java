package com.koi151.QTDL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QtdlApplication {

	public static void main(String[] args) {
		SpringApplication.run(QtdlApplication.class, args);
	}

}
