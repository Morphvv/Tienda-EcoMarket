package com.TiendaT.Tienda.repository;
import com.TiendaT.Tienda.model.HorarioPersonal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HorarioPersonalRepositoryTest {

    @Autowired
    private HorarioPersonalRepository horarioPersonalRepository;

    @Test
    void guardarHorarioP(){
        HorarioPersonal horario = new HorarioPersonal();
        horario.setIdAsignacion(1L);
        horario.setDiaSemana("LUNES");
        horario.setHoraInicio(LocalTime.of(8, 0));
        horario.setHoraTermino(LocalTime.of(17, 0));
        horario.setTurno("NOCHE");
        horario.setActivo(true);

        HorarioPersonal guardado = horarioPersonalRepository.save(horario);

        assertNotNull(guardado.getIdHorarioPersonal());
        assertEquals("LUNES", guardado.getDiaSemana());
    }

    @Test
    void buscarIdAsignacion(){
        HorarioPersonal h1 = new HorarioPersonal();
        h1.setIdAsignacion(1L);
        h1.setDiaSemana("LUNES");
        h1.setActivo(true);

        HorarioPersonal h2 = new HorarioPersonal();
        h2.setIdAsignacion(1L);
        h2.setDiaSemana("MARTES");
        h2.setActivo(true);

        HorarioPersonal h3 = new HorarioPersonal();
        h3.setIdAsignacion(2L);
        h3.setDiaSemana("MIERCOLES");
        h3.setActivo(true);

        horarioPersonalRepository.save(h1);
        horarioPersonalRepository.save(h2);
        horarioPersonalRepository.save(h3);

        List<HorarioPersonal> resultado = horarioPersonalRepository.findByIdAsignacion(1L);

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(h -> h.getIdAsignacion().equals(1L)));
    }

    @Test
    void buscarIdAsignacion_null(){
        List<HorarioPersonal> resultado = horarioPersonalRepository.findByIdAsignacion(99L);

        assertTrue(resultado.isEmpty());
    }
    
}
