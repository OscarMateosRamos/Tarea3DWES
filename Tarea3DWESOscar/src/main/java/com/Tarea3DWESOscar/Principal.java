package com.Tarea3DWESOscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.Tarea3DWESOscar.modelo.Planta;
import com.Tarea3DWESOscar.servicios.ServiciosPlanta;

public class Principal implements CommandLineRunner {
	@Autowired
	ServiciosPlanta servplanta;

//	@Autowired
//	ServiciosEjemplar servejemplar;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("INI");
		Planta p = new Planta();

		/// Toma de datos por el usuario
		if (servplanta.validarPlanta(p)) {
			servplanta.insertarPlanta(p);
		}

		System.out.println("------------------------");

		System.out.println("FIN");

	}

}
