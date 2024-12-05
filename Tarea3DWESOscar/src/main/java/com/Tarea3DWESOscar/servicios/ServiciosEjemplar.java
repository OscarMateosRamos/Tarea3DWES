package com.Tarea3DWESOscar.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tarea3DWESOscar.modelo.Ejemplar;
import com.Tarea3DWESOscar.modelo.Planta;
import com.Tarea3DWESOscar.repositories.EjemplarRepository;
import com.Tarea3DWESOscar.repositories.PlantaRepository;

@Service
public class ServiciosEjemplar {

	@Autowired
	PlantaRepository plantarepo;
	@Autowired
	EjemplarRepository ejemplarrepo;

	public void insertarEjemplar(Ejemplar ej) {
		ejemplarrepo.saveAndFlush(ej);
	}

	public void verEjemplar(Ejemplar ej) {
		ejemplarrepo.saveAndFlush(ej);
	}
	
	
	
}
