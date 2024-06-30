package com.gestion_tickets;

import com.gestion_tickets.repositories.AdminRepositorie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class GestionTicketsApplication {

	private final AdminRepositorie adminRepositorie;

	public GestionTicketsApplication(AdminRepositorie adminRepositorie) {
		this.adminRepositorie = adminRepositorie;
	}

	public static void main(String[] args) {

		SpringApplication.run(GestionTicketsApplication.class, args);
	}


}
