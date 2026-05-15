package com.TiendaT.Tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TiendaT.Tienda.model.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository <Tienda, Long>{
    
}
