package com.Tarea3DWESOscar.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tarea3DWESOscar.modelo.Planta;
import com.Tarea3DWESOscar.repositories.EjemplarRepository;
import com.Tarea3DWESOscar.repositories.PlantaRepository;

@Service
public class ServiciosPlanta {

	@Autowired
	PlantaRepository plantarepo;
	
//	@Autowired
//	EjemplarRepository ejemplarrepo;
	
	public boolean validarPlanta(Planta p) {
		
		return true;	
	}
	
	
	public void insertarPlanta(Planta p) {
		plantarepo.saveAndFlush(p);
	}
	
	
}
