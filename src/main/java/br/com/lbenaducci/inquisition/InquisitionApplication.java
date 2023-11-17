package br.com.lbenaducci.inquisition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InquisitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(InquisitionApplication.class, args);
	}

}
