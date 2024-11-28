package com.Tarea3DWESOscar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tarea3DWESOscar.modelo.Planta;
@Repository
public interface PlantaRepository extends JpaRepository <Planta,Long> {

}
