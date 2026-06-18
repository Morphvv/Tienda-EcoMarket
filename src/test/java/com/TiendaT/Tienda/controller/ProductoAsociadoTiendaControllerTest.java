package com.TiendaT.Tienda.controller;
import com.TiendaT.Tienda.model.ProductoAsociadoTienda;
import com.TiendaT.Tienda.service.ProductoAsociadoTiendaService;
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

@WebMvcTest(ProductoAsociadoTiendaController.class)
class ProductoAsociadoTiendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductoAsociadoTiendaService productoAsociadoTiendaService;

    @Test
    void crearProductoAsociado() throws Exception{
        ProductoAsociadoTienda producto = new ProductoAsociadoTienda();
        producto.setNombreProducto("Manzana Roja");
        producto.setVisibleEnTienda(true);

        when(productoAsociadoTiendaService.crearProductoAsociado(any(ProductoAsociadoTienda.class)));

        mockMvc.perform(post("/api/v1/ProductoAsociadoTienda/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombreProducto").value("Manzana Roja"))
            .andExpect(jsonPath("$.visibleEnTienda").value(true));
    }

    @Test
    void listarTiendaProducto() throws Exception{
        ProductoAsociadoTienda p1 = new ProductoAsociadoTienda();
        p1.setNombreProducto("Manzana Roja");
        ProductoAsociadoTienda p2 = new ProductoAsociadoTienda();
        p2.setNombreProducto("Pera");

        when(productoAsociadoTiendaService.listarTiendaProducto()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/v1/ProductoAsociadoTienda/listar"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].nombreProducto").value("Manzana Roja"));
    }

    @Test
    void listarPorTiendaProducto() throws Exception{
        ProductoAsociadoTienda p1 = new ProductoAsociadoTienda();
        p1.setIdTienda(1L);

        when(productoAsociadoTiendaService.listarPorTiendaProducto(1L)).thenReturn(List.of(p1));

        mockMvc.perform(get("/api/v1/ProductoAsociadoTienda/listar/tienda/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void modificarProductoTienda() throws Exception{
        ProductoAsociadoTienda datos = new ProductoAsociadoTienda();
        datos.setNombreProducto("Nombre nuevo");

        ProductoAsociadoTienda actualizado = new ProductoAsociadoTienda();
        actualizado.setIdProducto(1L);
        actualizado.setNombreProducto("Nombre nuevo");

        when(productoAsociadoTiendaService.modificarProductoTienda(eq(1L), any(ProductoAsociadoTienda.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/ProductoAsociadoTienda/modificar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombreProducto").value("Nombre nuevo"));
    }

    @Test
    void ocultarProductoAsociadoTienda() throws Exception{
        ProductoAsociadoTienda oculto = new ProductoAsociadoTienda();
        oculto.setIdProducto(1L);
        oculto.setVisibleEnTienda(false);

        when(productoAsociadoTiendaService.ocultarProductoAsociadoTienda(1L)).thenReturn(oculto);

        mockMvc.perform(put("/api/v1/ProductoAsociadoTienda/ocultar/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.visibleEnTienda").value(false));
    }

    @Test
    void eliminarProductoAsociadoTienda() throws Exception{
        doNothing().when(productoAsociadoTiendaService).eliminarProductoAsociadoTienda(1L);

        mockMvc.perform(delete("/api/v1/ProductoAsociadoTienda/eliminar/1"))
            .andExpect(status().isOk());
    }
    
}
