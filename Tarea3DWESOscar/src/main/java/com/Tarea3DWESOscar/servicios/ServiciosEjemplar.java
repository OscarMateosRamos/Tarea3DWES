package com.Tarea3DWESOscar.servicios;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tarea3DWESOscar.modelo.Ejemplar;
import com.Tarea3DWESOscar.modelo.Mensaje;
import com.Tarea3DWESOscar.modelo.Persona;
import com.Tarea3DWESOscar.repositories.EjemplarRepository;

@Service
public class ServiciosEjemplar {
	@Autowired
	EjemplarRepository ejemplarrepo;
	@Autowired
	ServiciosPersona servPersona;
	@Autowired
	ServiciosMensaje servMensaje;
	@Autowired
	Controlador controlador;

	public void insertarEjemplar(Ejemplar ej) {
		ejemplarrepo.saveAndFlush(ej);

		List<Ejemplar> ejemplares = ejemplarrepo.findAll();

		for (Ejemplar e : ejemplares) {
			if (e.getNombre().equals(ej.getNombre())) {
				String nuevoNombre = ej.getNombre() + "_" + e.getId();
				ej.setNombre(nuevoNombre);
				ejemplarrepo.saveAndFlush(ej);

				Mensaje m = new Mensaje();

				LocalDate fechahora = LocalDate.now();
				Date date = Date.valueOf(fechahora);

				Optional<Persona> p = servPersona.buscarPorId((long) 1);

				String mensaje = "Añadido nuevo ejemplar " + ej.getNombre() + " por " + controlador.getUsername() + " ("
						+ fechahora + " ).";
				m.setEjemplar(ej);

				m.setFechahora(date);

				m.setMensaje(mensaje);

				Optional<Persona> personas = servPersona.buscarPorId(Long.valueOf(1));
				m.setPersona(personas.get());
				servMensaje.insertar(m);

			}

		}

	}

	public ArrayList<Ejemplar> listaejemplaresPorTipoPlanta(String codigo) {
		List<Ejemplar> ejemplares = ejemplarrepo.ejemplaresPorTipoPlanta(codigo);
		return new ArrayList<>(ejemplares);
	}

	public Optional<Ejemplar> buscarPorNombre(String nombre) {
		Optional<Ejemplar> ejemplares = ejemplarrepo.findByNombre(nombre);

		return ejemplares;

	}

}
