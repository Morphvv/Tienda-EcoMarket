package com.TiendaT.Tienda.controller;
import com.TiendaT.Tienda.model.AsignacionPersonal;
import com.TiendaT.Tienda.service.AsignacionPersonalService;
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

@WebMvcTest(AsignacionPersonalController.class)
class AsigancionPersonalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AsignacionPersonalService asignacionPersonalService;

    @Test
    void crearAsignacionP() throws Exception{
        AsignacionPersonal asignacion = new AsignacionPersonal();
        asignacion.setIdTienda(1L);
        asignacion.setIdEmpleado(1L);
        asignacion.setNombreEmpleado("Juan Perez");
        asignacion.setCargo("Vendedor");
        asignacion.setEstadoAsignacion("ACTIVA");

        when(asignacionPersonalService.crearAsignacionP(any(AsignacionPersonal.class))).thenReturn(asignacion);

        mockMvc.perform(post("/api/v1/AsignacionPersonal/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(asignacion)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombreEmpleado").value("Juan Perez"))
            .andExpect(jsonPath("$.estadoAsignacion").value("ACTIVA"));
    }

    @Test
    void listarAsignacionP() throws Exception{
        AsignacionPersonal a1 = new AsignacionPersonal();
        a1.setNombreEmpleado("Juan Perez");
        AsignacionPersonal a2 = new AsignacionPersonal();
        a2.setNombreEmpleado("Maria Lopez");

        when(asignacionPersonalService.listarAsignacionPersonal()).thenReturn(List.of(a1, a2));

        mockMvc.perform(get("/api/v1/AsignacionPersonal/listar"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].nombreEmpleado").value("Juan Perez"));
    }

    @Test
    void listarPorTiendaAsignacionP() throws Exception{
        AsignacionPersonal a1 = new AsignacionPersonal();
        a1.setIdTienda(1L);

        when(asignacionPersonalService.listarPorTiendaAsignacionP(1L)).thenReturn(List.of(a1));

        mockMvc.perform(get("/api/v1/AsignacionPersonal/listar/tienda/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void modificarAsignacionP() throws Exception{
        AsignacionPersonal datos = new AsignacionPersonal();
        datos.setNombreEmpleado("Nombre nuevo");
        datos.setCargo("Gerente");

        AsignacionPersonal actualizada = new AsignacionPersonal();
        actualizada.setIdAsignacion(1L);
        actualizada.setNombreEmpleado("Nombre nuevo");
        actualizada.setCargo("Mantenimiento");

        when(asignacionPersonalService.modificarAsignacionP(eq(1L), any(AsignacionPersonal.class))).thenReturn(actualizada);

        mockMvc.perform(put("/api/v1/AsignacionPersonal/modificar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombreEmpleado").value("Nombre nuevo"))
            .andExpect(jsonPath("$.cargo").value("Mantenimiento"));
    }

    @Test
    void desactivarAsignacionP() throws Exception{
        AsignacionPersonal inactiva = new AsignacionPersonal();
        inactiva.setIdAsignacion(1L);
        inactiva.setEstadoAsignacion("INACTIVA");

        when(asignacionPersonalService.desactivarAsignacionP(1L)).thenReturn(inactiva);

        mockMvc.perform(put("/api/v1/AsignacionPersonal/desactivar/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.estadoAsignacion").value("INACTIVA"));
    }

    @Test
    void eliminarAsignacionP() throws Exception{
        doNothing().when(asignacionPersonalService).eliminarAsignacionP(1L);

        mockMvc.perform(delete("/api/v1/AsignacionPersonal/eliminar/1"))
            .andExpect(status().isOk());
    }
}
