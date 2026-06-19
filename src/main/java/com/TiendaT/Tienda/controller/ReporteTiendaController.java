package com.TiendaT.Tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TiendaT.Tienda.model.ReporteTienda;
import com.TiendaT.Tienda.service.ReporteTiendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/ReporteTienda")
@CrossOrigin(origins = "*")
@Tag(name = "Reporte tienda", description = "Gestion de los reportes de la tienda")

public class ReporteTiendaController {
    
    @Autowired
    private ReporteTiendaService reporteTiendaService;

    //Crear reporte de tienda 
    @Operation(summary = "Crear un reporte")
    @PostMapping("/Crear")
    public ReporteTienda crearReporteTienda(@Valid @RequestBody ReporteTienda reporteTienda){
        return reporteTiendaService.crearReporteTienda(reporteTienda);
    }

    //Listar todos los reportes de la tienda
    @Operation(summary = "Listar todos los reportes de la tienda")
    @GetMapping("/listar")
    public List<ReporteTienda> listarReporteTienda(){
        return reporteTiendaService.listarReporteTienda();
    }

    //Listar reporte por id de tienda
    @Operation(summary = "Buscar reporte por id de la tienda")
    @GetMapping("/listar/tienda/{idTienda}")
    public List<ReporteTienda> listarPorTiendaReporte(@PathVariable Long idTienda){
        return reporteTiendaService.listarPorTiendaReporte(idTienda);
    }

    //Modificar reporte por el id
    @Operation(summary = "Modificar reporte por su id")
    @PutMapping("/modificar/{idReporte}")
    public ReporteTienda modifcarReporte(@PathVariable Long idReporte, @RequestBody ReporteTienda reporteTienda){
        return reporteTiendaService.modificarReporte(idReporte, reporteTienda);
    }

    //Eliminar reporte por el id
    @Operation(summary = "Eliminar reporte por su id")
    @DeleteMapping("/eliminar/{idReporte}")
    public void eliminarReporte(@PathVariable Long idReporte){
        reporteTiendaService.eliminarReporte(idReporte);
    }

}
