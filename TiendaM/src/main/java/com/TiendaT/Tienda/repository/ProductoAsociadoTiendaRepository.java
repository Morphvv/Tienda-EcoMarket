package com.TiendaT.Tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TiendaT.Tienda.model.ProductoAsociadoTienda;

@Repository
public interface ProductoAsociadoTiendaRepository extends JpaRepository<ProductoAsociadoTienda, Long> {

}