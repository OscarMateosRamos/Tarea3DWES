package com.Tarea3DWESOscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;



public class Principal implements CommandLineRunner {
	
	@Autowired
	ServiciosPlanta servPlanta;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("INI");
		
//		
//		Planta p = new Planta();
//		servPlanta.validarPlanta(p);
//		
//		servPlanta.insertarPlanta(p);
		
		
		System.out.println("-------------------");
		
		
		System.out.println("FIN");
		
	}

}
