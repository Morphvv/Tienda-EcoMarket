package com.TiendaT.Tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TiendaT.Tienda.model.HorarioPersonal;
@Repository
public interface HorarioPersonalRepository extends JpaRepository<HorarioPersonal, Long> {
    List<HorarioPersonal> findByIdAsignacion(Long idAsignacion);

}