package com.TiendaT.Tienda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto_asociado_tienda")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductoAsociadoTienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductoAsociado;

    @NotNull
    private Long idTienda;
    @NotNull
    private Long idProducto;
    private String nombreProducto;
    private boolean visibleEnTienda;
    
}
