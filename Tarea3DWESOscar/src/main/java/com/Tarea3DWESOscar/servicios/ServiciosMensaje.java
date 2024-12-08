package com.Tarea3DWESOscar.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tarea3DWESOscar.modelo.Mensaje;
import com.Tarea3DWESOscar.repositories.MensajeRepository;

@Service
public class ServiciosMensaje {
	@Autowired
	MensajeRepository mensajerepo;

	public void insertar(Mensaje m) {
		mensajerepo.saveAndFlush(m);
	}

	
	public void verTodosMensajes() {
		List<Mensaje> mensajes = mensajerepo.findAll();
		System.out.println("---LISTADO DE MENSAJES-----");
		for (Mensaje m : mensajes) {
			System.out.println("Id: "+m.getId()+" fecha: "+m.getFechahora()+" Mensaje : "+m.getMensaje()+" Ejemplar: "+m.getEjemplar().getNombre()+" CREADO POR: "+m.getPersona().getNombre());
		}
		System.out.println("----------------------------");

	}
	
}
