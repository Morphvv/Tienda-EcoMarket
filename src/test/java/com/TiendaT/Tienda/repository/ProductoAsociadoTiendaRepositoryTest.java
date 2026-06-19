package com.TiendaT.Tienda.repository;
import com.TiendaT.Tienda.model.ProductoAsociadoTienda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoAsociadoTiendaRepositoryTest {

    @Autowired 
    private ProductoAsociadoTiendaRepository productoAsociadoTiendaRepository;

    @Test
    void guardarProductoAsociadoT(){
        ProductoAsociadoTienda producto = new ProductoAsociadoTienda();
        producto.setIdTienda(1L);
        producto.setIdProducto(10L);
        producto.setNombreProducto("Jugo de naranja");
        producto.setVisibleEnTienda(true);

        ProductoAsociadoTienda guardado = productoAsociadoTiendaRepository.save(producto);

        assertNotNull(guardado.getIdProductoAsociado());
        assertEquals("Jugo de naranja", guardado.getNombreProducto());
    }

    @Test
    void buscarProductoTienda(){
        ProductoAsociadoTienda p1 = new ProductoAsociadoTienda();
        p1.setIdTienda(1L);
        p1.setIdProducto(1L);
        p1.setNombreProducto("Manzana Organica");
        p1.setVisibleEnTienda(true);

        ProductoAsociadoTienda p2 = new ProductoAsociadoTienda();
        p2.setIdTienda(1L);
        p2.setIdProducto(2L);
        p2.setNombreProducto("Pera Organica");
        p2.setVisibleEnTienda(true);

        ProductoAsociadoTienda p3 = new ProductoAsociadoTienda();
        p3.setIdTienda(2L);
        p3.setIdProducto(3L);
        p3.setNombreProducto("Naranja Organica");
        p3.setVisibleEnTienda(true);

        productoAsociadoTiendaRepository.save(p1);
        productoAsociadoTiendaRepository.save(p2);
        productoAsociadoTiendaRepository.save(p3);

        List<ProductoAsociadoTienda> resultado = productoAsociadoTiendaRepository.findByIdTienda(1L);

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(p -> p.getIdTienda().equals(1L)));
    }

    @Test
    void buscarProductoIdTienda(){
        List<ProductoAsociadoTienda> resultado = productoAsociadoTiendaRepository.findByIdTienda(99L);

        assertTrue(resultado.isEmpty());
    }
    
}
