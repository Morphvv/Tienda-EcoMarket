package com.TiendaT.Tienda.service;

import com.TiendaT.Tienda.model.HorarioTienda;
import com.TiendaT.Tienda.repository.HorarioTiendaRepository;
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
class HorarioTiendaServiceTest {

    @Mock
    private HorarioTiendaRepository horarioTiendaRepository;

    @InjectMocks
    private HorarioTiendaService horarioTiendaService;

    @Test
    void crearHorarioTienda_debeAsignarActivoTrue() {
        HorarioTienda horario = new HorarioTienda();
        horario.setDiaSemana("LUNES");

        when(horarioTiendaRepository.save(any(HorarioTienda.class)))
                .thenAnswer(inv -> inv.getArgument(0, HorarioTienda.class));

        HorarioTienda resultado = horarioTiendaService.crearHorarioTienda(horario);

        assertTrue(resultado.getActivo());
        verify(horarioTiendaRepository, times(1)).save(horario);
    }

    @Test
    void listarHorarioTienda_debeRetornarTodosLosHorarios() {
        HorarioTienda h1 = new HorarioTienda();
        h1.setDiaSemana("LUNES");
        HorarioTienda h2 = new HorarioTienda();
        h2.setDiaSemana("MARTES");

        when(horarioTiendaRepository.findAll()).thenReturn(List.of(h1, h2));

        List<HorarioTienda> resultado = horarioTiendaService.listarHorarioTienda();

        assertEquals(2, resultado.size());
        verify(horarioTiendaRepository, times(1)).findAll();
    }

    @Test
    void listarPorTiendaHorario_debeRetornarHorariosDeLaTienda() {
        HorarioTienda h1 = new HorarioTienda();
        h1.setIdTienda(1L);

        when(horarioTiendaRepository.findByIdTienda(1L)).thenReturn(List.of(h1));

        List<HorarioTienda> resultado = horarioTiendaService.listarPorTiendaHorario(1L);

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdTienda());
    }

    @Test
    void modificarHorarioTienda_cuandoExiste_debeActualizarCampos() {
        HorarioTienda existente = new HorarioTienda();
        existente.setIdHorarioTienda(1L);
        existente.setDiaSemana("LUNES");
        existente.setHoraApertura(LocalTime.of(9, 0));
        existente.setHoraCierre(LocalTime.of(18, 0));

        HorarioTienda datos = new HorarioTienda();
        datos.setDiaSemana("MIERCOLES");
        datos.setHoraApertura(LocalTime.of(10, 0));
        datos.setHoraCierre(LocalTime.of(20, 0));

        when(horarioTiendaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(horarioTiendaRepository.save(any(HorarioTienda.class)))
                .thenAnswer(inv -> inv.getArgument(0, HorarioTienda.class));

        HorarioTienda resultado = horarioTiendaService.modificarHorarioTienda(1L, datos);

        assertEquals("MIERCOLES", resultado.getDiaSemana());
        assertEquals(LocalTime.of(10, 0), resultado.getHoraApertura());
        assertEquals(LocalTime.of(20, 0), resultado.getHoraCierre());
    }

    @Test
    void modificarHorarioTienda_cuandoNoExiste_debeRetornarNull() {
        when(horarioTiendaRepository.findById(99L)).thenReturn(Optional.empty());

        HorarioTienda resultado = horarioTiendaService.modificarHorarioTienda(99L, new HorarioTienda());

        assertNull(resultado);
        verify(horarioTiendaRepository, never()).save(any());
    }

    @Test
    void desactivarHorarioTienda_cuandoExiste_debeCambiarActivoAFalse() {
        HorarioTienda existente = new HorarioTienda();
        existente.setIdHorarioTienda(1L);
        existente.setActivo(true);

        when(horarioTiendaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(horarioTiendaRepository.save(any(HorarioTienda.class)))
                .thenAnswer(inv -> inv.getArgument(0, HorarioTienda.class));

        HorarioTienda resultado = horarioTiendaService.desactivarHorarioTienda(1L);

        assertFalse(resultado.getActivo());
    }

    @Test
    void desactivarHorarioTienda_cuandoNoExiste_debeRetornarNull() {
        when(horarioTiendaRepository.findById(99L)).thenReturn(Optional.empty());

        HorarioTienda resultado = horarioTiendaService.desactivarHorarioTienda(99L);

        assertNull(resultado);
    }

    @Test
    void eliminarHorarioTienda_debeInvocarDeleteById() {
        horarioTiendaService.eliminarHorarioTienda(1L);

        verify(horarioTiendaRepository, times(1)).deleteById(1L);
    }
}
