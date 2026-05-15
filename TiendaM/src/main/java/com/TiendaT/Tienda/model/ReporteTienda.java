package com.TiendaT.Tienda.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reporte_tienda")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReporteTienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    private Long idTienda;
    private String tipoReporte;
    private LocalDateTime periodoInicio;
    private LocalDateTime periodoFin;
    private LocalDateTime fechaGeneracion;
}
