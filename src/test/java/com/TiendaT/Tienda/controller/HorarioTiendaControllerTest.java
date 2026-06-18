package com.TiendaT.Tienda.controller;
import com.TiendaT.Tienda.model.HorarioTienda;
import com.TiendaT.Tienda.service.HorarioTiendaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HorarioTiendaController.class)
class HorarioTiendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HorarioTiendaService horarioTiendaService;

    @Test
    void crearHorarioTienda() throws Exception{
        HorarioTienda horario = new HorarioTienda();
        horario.setDiaSemana("LUNES");
        horario.setActivo("true");

        when(horarioTiendaService.crearHorarioTienda(any(HorarioTienda.class))).thenReturn(horario);

        mockMvc.perform(post("/api/v1/HorarioTienda/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(horario)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.diaSemana").value("LUNES"))
            .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void listarHorarioTienda() throws Exception{
        HorarioTienda h1 = new HorarioTienda();
        h1.setDiaSemana("LUNES");
        HorarioTienda h2 = new HorarioTienda();
        h2.setDiaSemana("MARTES");

        when(horarioTiendaService.listarHorarioTienda()).thenReturn(List.of(h1, h2));

        mockMvc.perform(get("/api/v1/HorarioTienda/listar"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].diaSemana").value("LUNES"));
    }

    @Test
    void listarPorTiendaHorario() throws Exception{
        HorarioTienda h1 = new HorarioTienda();
        h1.setIdTienda(1L);

        when(horarioTiendaService.listarPorTiendaHorario(1L)).thenReturn(List.of(h1));

        mockMvc.perform(get("/api/v1/HorarioTienda/listar/tienda/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void modificarHorarioTienda() throws Exception{
        HorarioTienda datos = new HorarioTienda();
        datos.setDiaSemana("MIERCOLES");

        HorarioTienda actualizada = new HorarioTienda();
        actualizada.setIdHorarioTienda(1L);
        actualizada.setDiaSemana("MIERCOLES");

        when(horarioTiendaService.modificarHorarioTienda(eq(1L), any(HorarioTienda.class))).thenReturn(actualizada);

        mockMvc.perform(put("/api/v1/HorarioTienda/modificar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.diaSemana").value("MIERCOLES"));
    }

    @Test
    void desactivarHorarioTienda() throws Exception{
        HorarioTienda desactivado = new HorarioTienda();
        desactivado.setIdHorarioTienda(1L);
        desactivado.setActivo(false);
        
        when(horarioTiendaService.desactivarHorarioTienda(1l)).thenReturn(desactivado);

        mockMvc.perform(put("/api/v1/HorarioTienda/desactivar/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.activo").value(false));
    }

    @Test
    void eliminarHorarioTienda() throws Exception{
        doNothing().when(horarioTiendaService).eliminarHorarioTienda(1L);

        mockMvc.perform(delete("/api/v1/HorarioTienda/eliminar/1"))
            .andExpect(status().isOk());
    }
    
}
