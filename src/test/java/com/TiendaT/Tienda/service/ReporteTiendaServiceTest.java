package com.TiendaT.Tienda.service;
import com.TiendaT.Tienda.model.ReporteTienda;
import com.TiendaT.Tienda.repository.ReporteTiendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReporteTiendaServiceTest {

    @Mock 
    private ReporteTiendaRepository reporteTiendaRepository;

    @InjectMocks
    private ReporteTiendaService reporteTiendaService;

    @Test
    void crearReporteTienda_debeAsignarFechaGeneracion() {
        ReporteTienda reporte = new ReporteTienda();
        reporte.setTipoReporte("VENTAS");

        when(reporteTiendaRepository.save(any(ReporteTienda.class)))
                .thenAnswer(inv -> inv.getArgument(0, ReporteTienda.class));

        ReporteTienda resultado = reporteTiendaService.crearReporteTienda(reporte);

        assertNotNull(resultado.getFechaGeneracion());
        verify(reporteTiendaRepository, times(1)).save(reporte);
    }

    @Test
    void listarReporteTenda(){
        ReporteTienda r1 = new ReporteTienda();
        ReporteTienda r2 = new ReporteTienda();

        when(reporteTiendaRepository.findAll()).thenReturn(List.of(r1, r2));

        List<ReporteTienda> resultado = reporteTiendaService.listarReporteTienda();

        assertEquals(2, resultado.size());
        verify(reporteTiendaRepository, times(1)).findAll();

    }

    @Test
    void listarPorTiendaReporte(){
        ReporteTienda r1 = new ReporteTienda();
        r1.setIdTienda(1L);

        when(reporteTiendaRepository.findByIdTienda(1L)).thenReturn(List.of(r1));

        List<ReporteTienda> resultado = reporteTiendaService.listarPorTiendaReporte(1L);

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdTienda());
    }

    @Test
    void modificarReporte(){
        ReporteTienda existente = new ReporteTienda();
        existente.setIdReporte(1L);
        existente.setTipoReporte("VENTAS");

        ReporteTienda datos = new ReporteTienda();
        datos.setTipoReporte("INVENTARIO");

        when(reporteTiendaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(reporteTiendaRepository.save(any(ReporteTienda.class)))
                .thenAnswer(inv -> inv.getArgument(0, ReporteTienda.class));

        ReporteTienda resultado = reporteTiendaService.modificarReporte(1L, datos);

        assertEquals("INVENTARIO", resultado.getTipoReporte());
    }

    @Test
    void modificarReporte_null(){
        when(reporteTiendaRepository.findById(99L)).thenReturn(Optional.empty());

        ReporteTienda resultado = reporteTiendaService.modificarReporte(99L, new ReporteTienda());

        assertNull(resultado);
        verify(reporteTiendaRepository, never()).save(any());
    }

    @Test
    void eliminarReporte(){
        reporteTiendaService.eliminarReporte(1L);

        verify(reporteTiendaRepository, times(1)).deleteById(1L);
    }
    
}
