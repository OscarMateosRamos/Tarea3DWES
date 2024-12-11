package com.Tarea3DWESOscar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Tarea3DWESOscar.modelo.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

	@Query("SELECT m FROM Mensaje m WHERE m.persona.id = :idPersona")
	List<Mensaje> mensajesPorIdPersona(@Param("idPersona") Long idPersona);

	@Query("SELECT m FROM Mensaje m WHERE m.ejemplar.id = :idEjemplar ORDER BY fechahora" )
	List<Mensaje> mensajesPorIdEjemplar(@Param("idEjemplar") Long id);
	
	@Query("SELECT m FROM Mensaje m WHERE m.ejemplar.planta.codigo=:codigo ORDER BY ejemplar")
	List<Mensaje> mensajesPorCodigoPlanta(String codigo);

	
//	SELECT e FROM Ejemplar e WHERE e.planta.codigo = :codigoPlanta
	
////
//	List<Mensaje> mensajesPorCodigoEjemplar(String codigo);
//
//	

}
