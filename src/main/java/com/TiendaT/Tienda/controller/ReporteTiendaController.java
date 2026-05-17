package com.TiendaT.Tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TiendaT.Tienda.model.ReporteTienda;
import com.TiendaT.Tienda.service.ReporteTiendaService;

@RestController
@RequestMapping("api/v1/ReporteTienda")
@CrossOrigin(origins = "*")

public class ReporteTiendaController {
    
    @Autowired
    private ReporteTiendaService reporteTiendaService;

    //Crear reporte de tienda 
    @PostMapping("/Crear")
    public ReporteTienda crearReporteTienda(@RequestBody ReporteTienda reporteTienda){
        return reporteTiendaService.crearReporteTienda(reporteTienda);
    }

    //Listar todos los reportes de la tienda
    @GetMapping("/listar")
    public List<ReporteTienda> listarReporteTienda(){
        return reporteTiendaService.listarReporteTienda();
    }

    //Listar reporte por id de tienda
    @GetMapping("/listar/tienda/{idTienda}")
    public List<ReporteTienda> listarPorTiendaReporte(@PathVariable Long idTienda){
        return reporteTiendaService.listarPorTiendaReporte(idTienda, reporteTienda);
    }

    //Modificar reporte por el id
    @PutMapping("/modificar/{idReporte}")
    public ReporteTienda modifcarReporte(@PathVariable Long idReporte, @RequestBody ReporteTienda reporteTienda){
        return reporteTiendaService.modificarReporte(idReporte, reporteTienda);
    }

    //Eliminar reporte por el id
    @DeleteMapping("/eliminar/{idReporte}")
    public void eliminarReporte(@PathVariable Long idReporte){
        reporteTiendaService.eliminarReporte(idReporte);
    }

}
