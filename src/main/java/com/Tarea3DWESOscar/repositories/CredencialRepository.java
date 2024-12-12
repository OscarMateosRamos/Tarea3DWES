package com.Tarea3DWESOscar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tarea3DWESOscar.modelo.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {

	String findByUsuario(String usuario);



}
