package com.Tarea3DWESOscar.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tarea3DWESOscar.modelo.Ejemplar;
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

		for (Mensaje m : mensajes) {
			System.out.println("Id: " + m.getId() + " fecha: " + m.getFechahora() + " Mensaje : " + m.getMensaje()
					+ " Ejemplar: " + m.getEjemplar().getNombre() + " CREADO POR: " + m.getPersona().getNombre());
		}

	}
	
	
	public List<Mensaje> listamensajesPorIdEjemplar(Long id) {
		List<Mensaje> mensajes = mensajerepo.mensajesPorIdEjemplar(id);
		return mensajes;
		
	}
	
	public List<Mensaje> listamensajesPorIdPersona(Long id) {
		List<Mensaje> mensajes = mensajerepo.mensajesPorIdPersona(id);
		return mensajes;
		
	}
	
	public List<Mensaje> listamensajesPorCodigoPlanta(String codigo) {
		List<Mensaje> mensajes = mensajerepo.mensajesPorCodigoPlanta(codigo);
		return mensajes;
		
	}

}
