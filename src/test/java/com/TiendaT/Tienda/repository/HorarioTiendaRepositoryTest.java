package com.TiendaT.Tienda.repository;

import com.TiendaT.Tienda.model.HorarioTienda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HorarioTiendaRepositoryTest {

    @Autowired
    private HorarioTiendaRepository horarioTiendaRepository;

    @Test
    void guardarHorarioT(){
        HorarioTienda horario = new HorarioTienda();
        horario.setIdTienda(1L);
        horario.setDiaSemana("LUNES");
        horario.setHoraApertura(LocalTime.of(9, 0));
        horario.setHoraCierre(LocalTime.of(18, 0));
        horario.setActivo(true);

        HorarioTienda guardado = horarioTiendaRepository.save(horario);

        assertNotNull(guardado.getIdHorarioTienda());
        assertEquals("LUNES", guardado.getDiaSemana());
    }

    @Test
    void buscarTiendaId(){
        HorarioTienda h1 = new HorarioTienda();
        h1.setIdTienda(1L);
        h1.setDiaSemana("LUNES");
        h1.setActivo(true);

        HorarioTienda h2 = new HorarioTienda();
        h2.setIdTienda(1L);
        h2.setDiaSemana("MARTES");
        h2.setActivo(true);

        HorarioTienda h3 = new HorarioTienda();
        h3.setIdTienda(2L);
        h3.setDiaSemana("MIERCOLES");
        h3.setActivo(true);

        horarioTiendaRepository.save(h1);
        horarioTiendaRepository.save(h2);
        horarioTiendaRepository.save(h3);

        List<HorarioTienda> resultado = horarioTiendaRepository.findByIdTienda(1L);

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(h -> h.getIdTienda().equals(1L)));
    }

    @Test
    void buscarTiendaId_null(){ //Cuando la tienda no existe
        List<HorarioTienda> resultado = horarioTiendaRepository.findByIdTienda(99L);

        assertTrue(resultado.isEmpty());
    }
    
}
