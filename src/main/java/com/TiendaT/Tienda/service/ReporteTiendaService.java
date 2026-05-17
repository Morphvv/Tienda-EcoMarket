package com.TiendaT.Tienda.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaT.Tienda.model.ReporteTienda;
import com.TiendaT.Tienda.repository.ReporteTiendaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class ReporteTiendaService {

    @Autowired
    private ReporteTiendaRepository reporteTiendaRepository;

    //Crear reporte de la tienda
    public ReporteTienda crear(ReporteTienda reporteTienda){
        reporteTienda.setFechaReporte(LocalDateTime.now());
        return reporteTiendaRepository.save(reporteTienda);
    }

    //Listar todos los reportes de la tienda
    public List <ReporteTienda> listar(){
        return reporteTiendaRepository.findAll();
    }

    //Listar reporte por id de tienda
    public List <ReporteTienda> listarPorTienda(long idTienda){
        return reporteTiendaRepository.findByIdTienda(idTienda);
    }

    //Modificar reporte de la tienda por el id 
    public ReporteTienda modificar(Long id, ReporteTienda reporteTienda){
        ReporteTienda existente = reporteTiendaRepository.findByIdReporteTienda(id).orElse(null);
        if (existente != null){
            existente.setTipoReporte(reporteTienda.getTipoReporte());
            existente.setPeriodoInicio(reporteTienda.getPeriodoInicio());
            existente.setPeriodoFin(reporteTienda.getPeriodoFin());
            return reporteTiendaRepository.save(existente);
        }
        return null;
    }

    //Eliminar reporte de la tienda por id 
    public void eliminar(Long id){
        reporteTiendaRepository.deleteById(id);
    }
    
}
