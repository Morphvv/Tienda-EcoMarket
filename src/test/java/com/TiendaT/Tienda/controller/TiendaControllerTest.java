package com.TiendaT.Tienda.controller;
import com.TiendaT.Tienda.model.Tienda;
import com.TiendaT.Tienda.service.TiendaService;
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


@WebMvcTest(TiendaController.class)
class TiendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TiendaService tiendaService;

    @Test
    void crearTienda() throws Exception{ 
        Tienda tienda = new Tienda();
        tienda.setNombre("EcoMarket central");
        tienda.setEstado("ACTIVA");

        when(tiendaService.crearTienda(any(Tienda.class))).thenReturn(tienda);

        mockMvc.perform(post("/api/v1/tienda/crear")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(tienda)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre").value("EcoMarket central"))
        .andExpect(jsonPath("$.estado").value("ACTIVA"));
    }

    @Test
    void listarTiendas() throws Exception{
        Tienda t1 = new Tienda();
        t1.setNombre("Tienda providencia");
        Tienda t2 = new Tienda();
        t2.setNombre("Tienda maipu");

        when(tiendaService.listarTiendas()).thenReturn(List.of(t1, t2));

        mockMvc.perform(get("/api/v1/tienda/listar"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$.nombre").value("Tienda providencia"));
    }

    @Test
    void buscarTiendaPorId() throws Exception{
        Tienda tienda = new Tienda();
        tienda.setIdTienda(1L);
        tienda.setNombre("EcoMarket sur");

        when(tiendaService.buscarTiendaPorId(1L)).thenReturn(tienda);

        mockMvc.perform(get("/api/v1/tienda/buscar/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("EcoMarket sur"));
    }

    @Test
    void buscarTiendaPorId_null() throws Exception{ //Cuando no existe la tienda
        when(tiendaService.buscarTiendaPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/tienda/buscar/99"))
            .andExpect(status().isOk());
    }

    @Test
    void modificarTienda() throws Exception{ //Retornar 200 con los datos actualizados
        Tienda datos = new Tienda();
        datos.setNombre("EcoMarket sur plus");

        Tienda actualizada = new Tienda();
        actualizada.setIdTienda(1L);
        actualizada.setNombre("EcoMarket sur plus");

        when(tiendaService.modificarTienda(eq(1L), any(Tienda.class))).thenReturn(actualizada);

        mockMvc.perform(put("/api/v1/tienda/modificar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("EcoMarket sur plus"));
    }

    @Test
    void desactivarTienda() throws Exception{
        Tienda inactiva = new Tienda();
        inactiva.setIdTienda(1L);
        inactiva.setEstado("INACTIVA");

        when(tiendaService.desactivarTienda(1L)).thenReturn(inactiva);

        mockMvc.perform(put("/api/v1/tienda/desactivar/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.estado").value("INACTIVA"));
    }

    @Test
    void eliminarTienda() throws Exception{
        doNothing().when(tiendaService).eliminarTienda(1L);

        mockMvc.perform(delete("/api/v1/tienda/eliminar/1"))
            .andExpect(status().isOk());
    }
    
}
