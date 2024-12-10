package com.Tarea3DWESOscar.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tarea3DWESOscar.modelo.Persona;
import com.Tarea3DWESOscar.repositories.PersonaRepository;

@Service
public class ServiciosPersona {

	@Autowired
	PersonaRepository personarepo;

	public boolean validarPersona(String nombre, String email) {

		if (nombre.length() > 255) {
			System.out.println("Nombre invalido");
			return false;
		}

		if (email.length() > 255) {
			System.out.println("Email invalido");
			return false;
		}
//
//		String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.(A-Za-z){2,3}$";
//
//		if (!email.matches(regex)) {
//			System.out.println("Formato de email invalido");
//			return false;
//		}

		List<Persona> personas = personarepo.findAll();
		for (Persona p : personas) {
			if (p.getEmail().equals(email)) {
				System.out.println("El email ya existe...." + email);
				return false;
			}

		}

		return true;

	}

	public void verTodasPersonas() {
		List<Persona> personas = personarepo.findAll();
		System.out.println("---LISTADO DE PERSONAS-----");
		for (Persona p : personas) {
			System.out.println("Id: " + p.getId() + " Nombre: " + p.getNombre() + " Email: " + p.getEmail()
					+ " Credenciales: " + p.getCredencial());
		}
		System.out.println("----------------------------");

	}

	public void insertarPersona(Persona p) {
		personarepo.saveAndFlush(p);
	}

	public Optional<Persona> buscarPorId(Long id) {
		return personarepo.findById(id);
	}

	public Persona buscarPorNombre(String nombre) {
		Persona per = new Persona();

		List<Persona> personas = personarepo.findAll();

		for (Persona p : personas) {
			if (p.getCredencial().getUsuario().equals(nombre)) {

				per = p;
			}
		}

		return per;
	}

	public boolean existeidPersona(long idPersona) {
		List<Persona> personas = personarepo.findAll();

		for (Persona p : personas) {
			if (p.getId().equals(idPersona)) {

				return true;
			}
		}
		return false;
	}
}
