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
@Table(name = "asignacion_personal")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AsignacionPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsignacion;

    private Long idTienda;
    private Long idEmpelado;
    private String nombreEmpleado;
    private String cargo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaTermino;
    private String estadoAsignacion;
    
}
