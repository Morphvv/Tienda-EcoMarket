package com.TiendaT.Tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TiendaT.Tienda.model.ReporteTienda;

@Repository
public interface ReporteTiendaRepository extends JpaRepository<ReporteTienda, Long> {

}