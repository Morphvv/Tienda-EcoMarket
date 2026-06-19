package com.TiendaT.Tienda.service;

import com.TiendaT.Tienda.client.CatalogoClient;
import com.TiendaT.Tienda.dto.ProductoDTO;
import com.TiendaT.Tienda.model.ProductoAsociadoTienda;
import com.TiendaT.Tienda.repository.ProductoAsociadoTiendaRepository;
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
class ProductoAsociadoTiendaServiceTest {

    @Mock
    private ProductoAsociadoTiendaRepository productoAsociadoTiendaRepository;

    @Mock
    private CatalogoClient catalogoClient;

    @InjectMocks
    private ProductoAsociadoTiendaService productoAsociadoTiendaService;

    @Test
    void crearProductoAsociad(){
        ProductoAsociadoTienda producto = new ProductoAsociadoTienda();
        producto.setIdProducto(10L);

        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(10L);
        dto.setNombre("Manzana Roja");
        dto.setDescripcion("Fruta");
        dto.setPrecio(100.0);
        dto.setEstado("ACTIVO");

        when(catalogoClient.obtenerProductoPorId(10L)).thenReturn(dto);
        when(productoAsociadoTiendaRepository.save(any(ProductoAsociadoTienda.class)))
            .thenAnswer(inv -> inv.getArgument(0, ProductoAsociadoTienda.class));

        ProductoAsociadoTienda resultado = productoAsociadoTiendaService.crearProductoAsociado(producto);

        assertEquals("Manzana Roja", resultado.getNombreProducto());
        assertTrue(resultado.isVisibleEnTienda());
        verify(productoAsociadoTiendaRepository, times(1)).save(producto);
    }

    @Test
    void crearProductoAsociado_productoNull(){
        ProductoAsociadoTienda producto = new ProductoAsociadoTienda();
        producto.setNombreProducto("Nombre manual");

        when(productoAsociadoTiendaRepository.save(any(ProductoAsociadoTienda.class)))
            .thenAnswer(inv -> inv.getArgument(0, ProductoAsociadoTienda.class));

        ProductoAsociadoTienda resultado = productoAsociadoTiendaService.crearProductoAsociado(producto);

        assertEquals("Nombre manual", resultado.getNombreProducto());
        assertTrue(resultado.isVisibleEnTienda());
    }

    @Test
    void crearProductoAsociado_excepcionNombreNull(){
        ProductoAsociadoTienda producto = new ProductoAsociadoTienda();
        producto.setIdProducto(1L);

        when(catalogoClient.obtenerProductoPorId(1L)).thenThrow(new RuntimeException("Error"));
        when(productoAsociadoTiendaRepository.save(any(ProductoAsociadoTienda.class)))
            .thenAnswer(inv -> inv.getArgument(0, ProductoAsociadoTienda.class));

        ProductoAsociadoTienda resultado = productoAsociadoTiendaService.crearProductoAsociado(producto);

        assertEquals("Producto no disponible", resultado.getNombreProducto());
        assertTrue(resultado.isVisibleEnTienda());
    }

    @Test
    void crearProductoAsociado_excepcionConNombre(){
        ProductoAsociadoTienda producto = new ProductoAsociadoTienda();
        producto.setIdProducto(1L);
        producto.setNombreProducto("Nombre existente");

        when(catalogoClient.obtenerProductoPorId(1L)).thenThrow(new RuntimeException("Error"));
        when(productoAsociadoTiendaRepository.save(any(ProductoAsociadoTienda.class)))
            .thenAnswer(inv -> inv.getArgument(0, ProductoAsociadoTienda.class));

        ProductoAsociadoTienda resultado = productoAsociadoTiendaService.crearProductoAsociado(producto);

        assertEquals("Nombre existente", resultado.getNombreProducto());
        assertTrue(resultado.isVisibleEnTienda());
    }

    @Test
    void listarTiendaProducto(){ //Mostrar todos los productos
        ProductoAsociadoTienda p1 = new ProductoAsociadoTienda();
        ProductoAsociadoTienda p2 = new ProductoAsociadoTienda();

        when(productoAsociadoTiendaRepository.findAll()).thenReturn(List.of(p1, p2));

        List<ProductoAsociadoTienda> resultado = productoAsociadoTiendaService.listarTiendaProducto();

        assertEquals(2, resultado.size());
        verify(productoAsociadoTiendaRepository, times(1)).findAll();
    }

    @Test
    void listarPorTiendaProducto(){
        ProductoAsociadoTienda p1 = new ProductoAsociadoTienda();
        p1.setIdTienda(1L);

        when(productoAsociadoTiendaRepository.findByIdTienda(1L)).thenReturn(List.of(p1));

        List<ProductoAsociadoTienda> resultado = productoAsociadoTiendaService.listarPorTiendaProducto(1L);

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdTienda());
    }

    @Test 
    void modificarProductoTienda(){ //Cuando existe
        ProductoAsociadoTienda existente = new ProductoAsociadoTienda();
        existente.setIdProductoAsociado(1L);
        existente.setNombreProducto("Nombre viejo");
        existente.setVisibleEnTienda(true);

        ProductoAsociadoTienda datos = new ProductoAsociadoTienda();
        datos.setNombreProducto("Nombre nuevo");
        datos.setVisibleEnTienda(false);

        when(productoAsociadoTiendaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(productoAsociadoTiendaRepository.save(any(ProductoAsociadoTienda.class)))
            .thenAnswer(inv -> inv.getArgument(0, ProductoAsociadoTienda.class));

        ProductoAsociadoTienda resultado = productoAsociadoTiendaService.modificarProductoTienda(1L, datos);

        assertEquals("Nombre nuevo", resultado.getNombreProducto());
        assertFalse(resultado.isVisibleEnTienda());
    }

    @Test
    void modificarProductoTienda_null(){ //Cuando no existe
        when(productoAsociadoTiendaRepository.findById(99L)).thenReturn(Optional.empty());

        ProductoAsociadoTienda resultado = productoAsociadoTiendaService.modificarProductoTienda(99L, new ProductoAsociadoTienda());

        assertNull(resultado);
        verify(productoAsociadoTiendaRepository, never()).save(any());
    }

    @Test
    void ocultarProductoAsociadoTienda(){ //Cuando existe y cambiamos el visibleEnTienda a false
        ProductoAsociadoTienda existente = new ProductoAsociadoTienda();
        existente.setIdProductoAsociado(1L);
        existente.setVisibleEnTienda(true);

        when(productoAsociadoTiendaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(productoAsociadoTiendaRepository.save(any(ProductoAsociadoTienda.class)))
            .thenAnswer(inv -> inv.getArgument(0, ProductoAsociadoTienda.class));

        ProductoAsociadoTienda resultado = productoAsociadoTiendaService.ocultarProductoAsociadoTienda(1L);

        assertFalse(resultado.isVisibleEnTienda());
    }

    @Test
    void ocultarProductoAsociadoTienda_null(){ //Cuando no existe
        when(productoAsociadoTiendaRepository.findById(99L)).thenReturn(Optional.empty());

        ProductoAsociadoTienda resultado = productoAsociadoTiendaService.ocultarProductoAsociadoTienda(99L);

        assertNull(resultado);
    }

    @Test
    void eliminarProductoAsociadoTienda(){
        productoAsociadoTiendaService.eliminarProductoAsociadoTienda(1L);

        verify(productoAsociadoTiendaRepository, times(1)).deleteById(1L);
    }
}
