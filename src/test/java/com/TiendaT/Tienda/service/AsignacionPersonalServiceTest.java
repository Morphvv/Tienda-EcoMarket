package com.TiendaT.Tienda.service;
import com.TiendaT.Tienda.model.AsignacionPersonal;
import com.TiendaT.Tienda.repository.AsignacionPersonalRepository;
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
class AsignacionPersonalServiceTest {

    @Mock
    private AsignacionPersonalRepository asignacionPersonalRepository;

    @InjectMocks
    private AsignacionPersonalService asignacionPersonalService;

    @Test
    void crearAsignacionP(){ //Asignar estado activo
        AsignacionPersonal asignacion = new AsignacionPersonal();
        asignacion.setNombreEmpleado("Juan Perez");

        when(asignacionPersonalRepository.save(any(AsignacionPersonal.class)))
            .thenAnswer(inv -> inv.getArgument(0, AsignacionPersonal.class));

            AsignacionPersonal resultado = asignacionPersonalService.crearAsignacionP(asignacion);

            assertEquals("ACTIVA", resultado.getEstadoAsignacion());
            verify(asignacionPersonalRepository, times(1)).save(asignacion);
    }

    @Test
    void listarAsignacionPersonal(){ //Retornar todas las asignaciones
        AsignacionPersonal a1 = new AsignacionPersonal();
        AsignacionPersonal a2 = new AsignacionPersonal();
        
        when(asignacionPersonalRepository.findAll()).thenReturn(List.of(a1, a2));

        List<AsignacionPersonal> resultado = asignacionPersonalService.listarAsignacionPersonal();

        assertEquals(2, resultado.size());
        verify(asignacionPersonalRepository, times(1)).findAll();
    }

    @Test
    void listarPorTiendaAsignacionP(){ //Retornar todas las asignaciones de esa tienda
        AsignacionPersonal a1 = new AsignacionPersonal();
        a1.setIdTienda(1L);

        when(asignacionPersonalRepository.findByIdTienda(1L)).thenReturn(List.of(a1));

        List<AsignacionPersonal> resultado = asignacionPersonalService.listarPorTiendaAsignacionP(1L);

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdTienda());
    }

    @Test
    void modificarAsignacionP(){ //Cuando existe la asignacion
        AsignacionPersonal existente = new AsignacionPersonal();
        existente.setIdAsignacion(1L);
        existente.setNombreEmpleado("Vicente");
        existente.setCargo("Reponedor");

        AsignacionPersonal datos = new AsignacionPersonal();
        datos.setNombreEmpleado("Vicente Aviles");
        datos.setCargo("Cajero");

        when(asignacionPersonalRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(asignacionPersonalRepository.save(any(AsignacionPersonal.class)))
            .thenAnswer(inv -> inv.getArgument(0, AsignacionPersonal.class));

            AsignacionPersonal resultado = asignacionPersonalService.modificarAsignacionP(1L, datos);

            assertEquals("Vicente Aviles", resultado.getNombreEmpleado());
            assertEquals("Cajero", resultado.getCargo());
    }

    @Test
    void modificarAsignacionP_null(){ //Cuando no existe y debe retornar un null
        when(asignacionPersonalRepository.findById(99L)).thenReturn(Optional.empty());

        AsignacionPersonal resultado = asignacionPersonalService.modificarAsignacionP(99L, new AsignacionPersonal());

        assertNull(resultado);
        verify(asignacionPersonalRepository, never()).save(any());
    }

    @Test
    void desactivarAsignacionP(){
        AsignacionPersonal existente = new AsignacionPersonal();
        existente.setIdAsignacion(1L);
        existente.setEstadoAsignacion("ACTIVA");

        when(asignacionPersonalRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(asignacionPersonalRepository.save(any(AsignacionPersonal.class)))
            .thenAnswer(inv -> inv.getArgument(0, AsignacionPersonal.class));

            AsignacionPersonal resultado = asignacionPersonalService.desactivarAsignacionP(1L);

            assertEquals("INACTIVA", resultado.getEstadoAsignacion());
    }

    @Test
    void desactivarAsignacionP_null (){ //Cuando no existe la asignacion
        when(asignacionPersonalRepository.findById(99L)).thenReturn(Optional.empty());

        AsignacionPersonal resultado = asignacionPersonalService.desactivarAsignacionP(99L);

        assertNull(resultado);
    }

    @Test
    void eliminarAsignacionP(){
        asignacionPersonalService.eliminarAsignacionP(1L);

        verify(asignacionPersonalRepository, times(1)).deleteById(1L);
    }
    
}
