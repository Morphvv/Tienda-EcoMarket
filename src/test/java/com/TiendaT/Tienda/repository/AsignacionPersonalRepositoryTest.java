package com.TiendaT.Tienda.repository;
import com.TiendaT.Tienda.model.AsignacionPersonal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AsignacionPersonalRepositoryTest {

    @Autowired
    private AsignacionPersonalRepository asignacionPersonalRepository;

    @Test
    void guardarAsignacion(){
        AsignacionPersonal asignacion = new AsignacionPersonal();
        asignacion.setIdTienda(1L);
        asignacion.setNombreEmpleado("Juan Perez");
        asignacion.setCargo("Vendedor");
        asignacion.setEstadoAsignacion("ACTIVA");

        AsignacionPersonal guardada = asignacionPersonalRepository.save(asignacion);

        assertNotNull(guardada.getIdAsignacion());
        assertEquals("Juan Perez", guardada.getNombreEmpleado());
    }

    @Test
    void buscarTiendaId(){
        AsignacionPersonal a1 = new AsignacionPersonal();
        a1.setIdTienda(1L);
        a1.setNombreEmpleado("Juan Perez");
        a1.setEstadoAsignacion("ACTIVA");

        AsignacionPersonal a2 = new AsignacionPersonal();
        a2.setIdTienda(1L);
        a2.setNombreEmpleado("Maria Lopez");
        a2.setEstadoAsignacion("ACTIVA");

        AsignacionPersonal a3 = new AsignacionPersonal();
        a3.setIdTienda(2L);
        a3.setNombreEmpleado("Pedro Soto");
        a3.setEstadoAsignacion("ACTIVA");

        asignacionPersonalRepository.save(a1);
        asignacionPersonalRepository.save(a2);
        asignacionPersonalRepository.save(a3);

        List<AsignacionPersonal> resultado = asignacionPersonalRepository.findByIdTienda(1L);

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(a -> a.getIdTienda().equals(1L)));
    }

    @Test 
    void buscarTiendaId_Null(){
        List<AsignacionPersonal> resultado = asignacionPersonalRepository.findByIdTienda(99L);

        assertTrue(resultado.isEmpty());
    }
    
}
