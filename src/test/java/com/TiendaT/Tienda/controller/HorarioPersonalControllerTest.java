package com.TiendaT.Tienda.controller;
import com.TiendaT.Tienda.model.HorarioPersonal;
import com.TiendaT.Tienda.service.HorarioPersonalService;
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

@WebMvcTest(HorarioPersonalController.class)
class HorarioPersonalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HorarioPersonalService horarioPersonalService;

    @Test
    void crearHorarioP() throws Exception{
        HorarioPersonal horario = new HorarioPersonal();
        horario.setDiaSemana("LUNES");
        horario.setActivo(true);
        
        when(horarioPersonalService.crear(any(HorarioPersonal.class))).thenReturn(horario);

        mockMvc.perform(post("/api/v1/HorarioPersonal/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .conetnt(objectMapper.writesValueAsString(horario)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.diaSemana").value("LUNES"))
            .andExpect(jsonPath("$.activo").value(true));
    }

    @Test
    void listarHorariosP() throws Exception{
        HorarioPersonal h1 = new HorarioPersonal();
        h1.setDiaSemana("LUNES");
        HorarioPersonal h2 = new HorarioPersonal();
        h2.setDiaSemana("MARTES");

        when(horarioPersonalService.listar()).thenReturn(List.of(h1, h2));

        mockMv.perform(get("/api/v1/HorarioPersonal/listar"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$.[0].diaSemana").value("LUNES"));
    }

    @Test
    void listarPorAsignacion() throws Exception{
        HorarioPersonal h1 = new HorarioPersonal();
        h1.setIdAsignacion(1l);

        when(horarioPersonalService.listarPorAsignacion(1L)).thenReturn(List.of(h1));

        mockMvc.perform(get("/api/v1/HorarioPersonal/listar/asignacion/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void modificarHorarioP() throws Exception{
        HorarioPersonal datos = new HorarioPersonal();
        datos.setDiaSemana("VIERNES");
        datos.setTurno("TARDE");

        HorarioPersonal actualizado = new HorarioPersonal();
        actualizado.setIdHorarioPersonal(1L);
        actualizado.setDiaSemana("VIERNES");
        actualizado.setTurno("TARDE");

        when(horarioPersonalService.modificar(eq(1L), any(HorarioPersonal.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/HorarioPersonal/modificar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.diaSemana").value("VIERNES"))
            .andExpect(jsonPath("$.turno").value("TARDE"));
    }

    @Test
    void desactivarHorarioP() throws Exception{
        HorarioPersonal desactivado = new HorarioPersonal();
        desactivado.setIdHorarioPersonal(1L);
        desactivado.setActivo(false);

        when(horarioPersonalService.desactivar(1L)).thenReturn(desactivado);

        mockMvc.perform(put("/api/v1/HorarioPersonal/desactivar/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.activo").value(false));
    }   
    
    @Test
    void eliminarHorarioP() throws Exception{
        doNothing().when(horarioPersonalService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/HorarioPersonal/eliminar/1"))
            .andExpect(status().isOk());
    }
}
