package com.TiendaT.Tienda.repository;

import com.TiendaT.Tienda.model.ReporteTienda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReporteTiendaRepositoryTest {

    @Autowired 
    private ReporteTiendaRepository reporteTiendaRepository;

    @Test
    void guardarReporteT(){
        ReporteTienda reporte = new ReporteTienda();
        reporte.setIdTienda(1L);
        reporte.setTipoReporte("VENTAS");
        reporte.setFechaGeneracion(LocalDateTime.now());

        ReporteTienda guardado = reporteTiendaRepository.save(reporte);

        assertNotNull(guardado.getIdReporte());
        assertEquals("VENTAS", guardado.getTipoReporte());
    }

    @Test
    void buscarReporteIdTienda(){
        ReporteTienda r1 = new ReporteTienda();
        r1.setIdTienda(1L);
        r1.setTipoReporte("VENTAS");
        r1.setFechaGeneracion(LocalDateTime.now());

        ReporteTienda r2 = new ReporteTienda();
        r2.setIdTienda(1L);
        r2.setTipoReporte("INVENTARIO");
        r2.setFechaGeneracion(LocalDateTime.now());

        ReporteTienda r3 = new ReporteTienda();
        r3.setIdTienda(2L);
        r3.setTipoReporte("VENTAS");
        r3.setFechaGeneracion(LocalDateTime.now());

        reporteTiendaRepository.save(r1);
        reporteTiendaRepository.save(r2);
        reporteTiendaRepository.save(r3);

        List<ReporteTienda> resultado = reporteTiendaRepository.findByIdTienda(1L);

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(r -> r.getIdTienda().equals(1L)));
    }

    @Test
    void buscarReporteIdTienda_null(){
        List<ReporteTienda> resultado = reporteTiendaRepository.findByIdTienda(99L);

        assertTrue(resultado.isEmpty());
    }
    
}
