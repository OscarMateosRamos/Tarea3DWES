package com.Tarea3DWESOscar.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tarea3DWESOscar.modelo.Planta;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long> {

	List<Planta> findAllByOrderByNombrecomunAsc();

	Optional<Planta> findByCodigo(String codigo);

	boolean existsByCodigo(String codigo);

}
