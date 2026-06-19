package com.TiendaT.Tienda.controller;
import com.TiendaT.Tienda.model.ReporteTienda;
import com.TiendaT.Tienda.service.ReporteTiendaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReporteTiendaController.class)
class ReporteTiendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReporteTiendaService reporteTiendaService;

    @Test
    void crearReporteTienda() throws Exception{
        ReporteTienda reporte = new ReporteTienda();
        reporte.setIdTienda(1L);
        reporte.setTipoReporte("VENTAS");

        when(reporteTiendaService.crearReporteTienda(any(ReporteTienda.class))).thenReturn(reporte);

        mockMvc.perform(post("/api/v1/ReporteTienda/Crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reporte)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoReporte").value("VENTAS"));
    }

    @Test
    void listarReporteTienda() throws Exception{
        ReporteTienda r1 = new ReporteTienda();
        r1.setTipoReporte("VENTAS");
        ReporteTienda r2 = new ReporteTienda();
        r2.setTipoReporte("INVENTARIO");

        when(reporteTiendaService.listarReporteTienda()).thenReturn(List.of(r1, r2));

        mockMvc.perform(get("/api/v1/ReporteTienda/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].tipoReporte").value("VENTAS"));
    }

    @Test
    void listarPorTiendaReporte() throws Exception{
        ReporteTienda r1 = new ReporteTienda();
        r1.setIdTienda(1L);
        when(reporteTiendaService.listarPorTiendaReporte(1L)).thenReturn(List.of(r1));

        mockMvc.perform(get("/api/v1/ReporteTienda/listar/tienda/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void modificarReporte() throws Exception{
        ReporteTienda datos = new ReporteTienda();
        datos.setTipoReporte("INVENTARIO");

        ReporteTienda actualizado = new ReporteTienda();
        actualizado.setIdReporte(1L);
        actualizado.setTipoReporte("INVENTARIO");

        when(reporteTiendaService.modificarReporte(eq(1L), any(ReporteTienda.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/ReporteTienda/modificar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoReporte").value("INVENTARIO"));
    }

    @Test
    void eliminarReporte() throws Exception{
        doNothing().when(reporteTiendaService).eliminarReporte(1L);

        mockMvc.perform(delete("/api/v1/ReporteTienda/eliminar/1"))
            .andExpect(status().isOk());
    }
    
}
