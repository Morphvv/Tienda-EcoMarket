package com.TiendaT.Tienda.service;

import com.TiendaT.Tienda.model.Tienda;
import com.TiendaT.Tienda.repository.TiendaRepository;
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
class TiendaServiceTest {

    @Mock
    private TiendaRepository tiendaRepository;

    @InjectMocks
    private TiendaService tiendaService;

    @Test
    void crearTienda_debeAsignarEstadoActivaYFechaCreacion() {
        Tienda tienda = new Tienda();
        tienda.setNombre("EcoMarket Central");

        when(tiendaRepository.save(any(Tienda.class))).thenAnswer(inv -> inv.getArgument(0, Tienda.class));

        Tienda resultado = tiendaService.crearTienda(tienda);

        assertEquals("ACTIVA", resultado.getEstado());
        assertNotNull(resultado.getFechaCreacion());
        verify(tiendaRepository, times(1)).save(tienda);
    }

    @Test
    void listarTiendas_debeRetornarListaDeTiendas() {
        Tienda t1 = new Tienda();
        t1.setNombre("Tienda Norte");
        Tienda t2 = new Tienda();
        t2.setNombre("Tienda Sur");

        when(tiendaRepository.findAll()).thenReturn(List.of(t1, t2));

        List<Tienda> resultado = tiendaService.listarTiendas();

        assertEquals(2, resultado.size());
        verify(tiendaRepository, times(1)).findAll();
    }

    @Test
    void buscarTiendaPorId_cuandoExiste_debeRetornarTienda() {
        Tienda tienda = new Tienda();
        tienda.setIdTienda(1L);
        tienda.setNombre("EcoMarket Sur");

        when(tiendaRepository.findById(1L)).thenReturn(Optional.of(tienda));

        Tienda resultado = tiendaService.buscarTiendaPorId(1L);

        assertNotNull(resultado);
        assertEquals("EcoMarket Sur", resultado.getNombre());
    }

    @Test
    void buscarTiendaPorId_cuandoNoExiste_debeRetornarNull() {
        when(tiendaRepository.findById(99L)).thenReturn(Optional.empty());

        Tienda resultado = tiendaService.buscarTiendaPorId(99L);

        assertNull(resultado);
    }

    @Test
    void modificarTienda_cuandoExiste_debeActualizarCampos() {
        Tienda existente = new Tienda();
        existente.setIdTienda(1L);
        existente.setNombre("Nombre Viejo");
        existente.setDireccion("Direccion Vieja");
        existente.setTelefono("111111");

        Tienda datos = new Tienda();
        datos.setNombre("Nombre Nuevo");
        datos.setDireccion("Direccion Nueva");
        datos.setTelefono("999999");

        when(tiendaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(tiendaRepository.save(any(Tienda.class))).thenAnswer(inv -> inv.getArgument(0, Tienda.class));

        Tienda resultado = tiendaService.modificarTienda(1L, datos);

        assertEquals("Nombre Nuevo", resultado.getNombre());
        assertEquals("Direccion Nueva", resultado.getDireccion());
        assertEquals("999999", resultado.getTelefono());
    }

    @Test
    void modificarTienda_cuandoNoExiste_debeRetornarNull() {
        when(tiendaRepository.findById(99L)).thenReturn(Optional.empty());

        Tienda resultado = tiendaService.modificarTienda(99L, new Tienda());

        assertNull(resultado);
        verify(tiendaRepository, never()).save(any());
    }

    @Test
    void desactivarTienda_cuandoExiste_debeCambiarEstadoAInactiva() {
        Tienda existente = new Tienda();
        existente.setIdTienda(1L);
        existente.setEstado("ACTIVA");

        when(tiendaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(tiendaRepository.save(any(Tienda.class))).thenAnswer(inv -> inv.getArgument(0, Tienda.class));

        Tienda resultado = tiendaService.desactivarTienda(1L);

        assertEquals("INACTIVA", resultado.getEstado());
    }

    @Test
    void desactivarTienda_cuandoNoExiste_debeRetornarNull() {
        when(tiendaRepository.findById(99L)).thenReturn(Optional.empty());

        Tienda resultado = tiendaService.desactivarTienda(99L);

        assertNull(resultado);
    }

    @Test
    void eliminarTienda_debeInvocarDeleteById() {
        tiendaService.eliminarTienda(1L);

        verify(tiendaRepository, times(1)).deleteById(1L);
    }
}
