package com.TiendaT.Tienda.service;

import com.TiendaT.Tienda.model.HorarioPersonal;
import com.TiendaT.Tienda.repository.HorarioPersonalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HorarioPersonalServiceTest {
    
    @Mock
    private HorarioPersonalRepository horarioPersonalRepository;

    @InjectMocks
    private HorarioPersonalService horarioPersonalService;

    @Test
    void crear_Asignacion(){
        HorarioPersonal horario = new HorarioPersonal();
        horario.setDiaSemana("LUNES");

        when(horarioPersonalRepository.save(any(HorarioPersonal.class)))
                .thenAnswer(inv -> inv.getArgument(0, HorarioPersonal.class));

        HorarioPersonal resultado = horarioPersonalService.crear(horario);

        assertTrue(resultado.getActivo());
        verify(horarioPersonalRepository, times(1)).save(horario);
    }

    @Test
    void listarHorariosP(){
        HorarioPersonal h1 = new HorarioPersonal();
        HorarioPersonal h2 = new HorarioPersonal();

        when(horarioPersonalRepository.findAll()).thenReturn(List.of(h1, h2));

        List<HorarioPersonal> resultado = horarioPersonalService.listar();

        assertEquals(2, resultado.size());
        verify(horarioPersonalRepository, times(1)).findAll();
    }

    @Test
    void listarPorAsignacion(){
        HorarioPersonal h1 = new HorarioPersonal();
        h1.setIdAsignacion(1L);

        when(horarioPersonalRepository.findByIdAsignacion(1L)).thenReturn(List.of(h1));

        List<HorarioPersonal> resultado = horarioPersonalService.listarPorAsignacion(1L);

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdAsignacion());
    }

    @Test
    void modificarHorarioP(){
        HorarioPersonal existente = new HorarioPersonal();
        existente.setIdHorarioPersonal(1L);
        existente.setDiaSemana("LUNES");
        existente.setTurno("MANANA");

        HorarioPersonal datos = new HorarioPersonal();
        datos.setDiaSemana("VIERNES");
        datos.setHoraInicio(LocalTime.of(14, 0));
        datos.setHoraTermino(LocalTime.of(22, 0));
        datos.setTurno("TARDE");

        when(horarioPersonalRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(horarioPersonalRepository.save(any(HorarioPersonal.class)))
                .thenAnswer(inv -> inv.getArgument(0, HorarioPersonal.class));

        HorarioPersonal resultado = horarioPersonalService.modificar(1L, datos);

        assertEquals("VIERNES", resultado.getDiaSemana());
        assertEquals("TARDE", resultado.getTurno());
        assertEquals(LocalTime.of(14, 0), resultado.getHoraInicio());
    }

    @Test
    void modificarHorarioP_null(){
        when(horarioPersonalRepository.findById(99L)).thenReturn(Optional.empty());

        HorarioPersonal resultado = horarioPersonalService.modificar(99L, new HorarioPersonal());

        assertNull(resultado);
        verify(horarioPersonalRepository, never()).save(any());
    }
    
    @Test
    void desactivarHorarioP(){
        HorarioPersonal existente = new HorarioPersonal();
        existente.setIdHorarioPersonal(1L);
        existente.setActivo(true);

        when(horarioPersonalRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(horarioPersonalRepository.save(any(HorarioPersonal.class)))
                .thenAnswer(inv -> inv.getArgument(0, HorarioPersonal.class));

        HorarioPersonal resultado = horarioPersonalService.desactivar(1L);

        assertFalse(resultado.getActivo());
    }

    @Test
    void desactivarHorarioP_null(){
        when(horarioPersonalRepository.findById(99L)).thenReturn(Optional.empty());

        HorarioPersonal resultado = horarioPersonalService.desactivar(99L);

        assertNull(resultado);
    }

    @Test
    void eliminarHorarioP(){
        horarioPersonalService.eliminar(1L);

        verify(horarioPersonalRepository, times(1)).deleteById(1L);
    }
}
