package com.mario.rayas.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Desarrolldo por Mario Sergio Rayas Chávez
Correo mrayas@gmail.com
Fecha Mayo 8, 2022
Definicion del modelo, objeto y la tabla en base de datos mySQL utilizando persistencia con JPA
*/

@SpringBootApplication
public class AtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmApplication.class, args);
	}

}
