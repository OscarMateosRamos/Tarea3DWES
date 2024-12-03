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

	public boolean validarPlanta(Planta pl) {

		if (pl.getCodigo().isEmpty()) {

			return false;
		}

		if (pl.getCodigo().length() < 3 || pl.getCodigo().length() > 20) {
			return false;
		}

		if (plantarepo.findById(pl)) {
			System.out.println("Ya existe el codigo");
			return false;
		}

		if (pl.getNombrecientifico().length() > 45) {
			return false;
		}

		if (pl.getNombrecomun().length() > 40) {
			return false;
		}

		return true;

	}

	public void insertarPlanta(Planta pl) {
		plantarepo.saveAndFlush(pl);
	}
	
	
	public void modificarPlanta(Planta pl) {
		plantarepo.saveAndFlush(pl);
	}
	
	public void verPlanta(Planta pl) {
		plantarepo.findAll();
	}
	
	
}
