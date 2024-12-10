package com.Tarea3DWESOscar.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tarea3DWESOscar.modelo.Credencial;
import com.Tarea3DWESOscar.modelo.Planta;
import com.Tarea3DWESOscar.repositories.CredencialRepository;

@Service
public class ServiciosCredenciales {

	@Autowired
	CredencialRepository credencialrepo;

	public boolean existeCredencial(String usuario) {
		List<Credencial> credenciales = credencialrepo.findAll();

		for (Credencial c : credenciales) {
			if (c.getUsuario().equals(usuario)) {
				System.out.println("Ya  existe: " + c.getUsuario() + usuario);
				return true;
			}
		}
		return false;
	}

	public void insertarCredencial(Credencial c) {
		credencialrepo.saveAndFlush(c);
	}

	public void findByUsuario(String usuario) {
		credencialrepo.findByUsuario(usuario);

	}

	public boolean verificaUsuario(String usuario, String password) {
		List<Credencial> credenciales = credencialrepo.findAll();
		for (Credencial c : credenciales) {
			if (c.getUsuario().equals(usuario) && c.getPassword().equals(password)) {
				return true;
			}
		}

		return false;
	}

}
