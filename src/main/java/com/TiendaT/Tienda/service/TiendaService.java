package com.TiendaT.Tienda.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaT.Tienda.model.Tienda;
import com.TiendaT.Tienda.repository.TiendaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    public Tienda crear(Tienda tienda){
        tienda.setFechaCreacion(LocalDateTime.now());
        tienda.setEstado("ACTIVA");
        return tiendaRepository.save(tienda);
    }

    public List<Tienda> listar(){
        return tiendaRepository.findAll();
    }
    
    public Tienda buscarPorId(Long idTienda){
        return tiendaRepository.findbyIdTienda(idTienda);
    }

    public Tienda modificar(Long id, Tienda tienda){
        Tienda tiendaExistente = tiendaRepository.findByIdTienda(id);
        if (tiendaExistente != null) {
            tiendaExistente.setNombre(tienda.getNombre());
            tiendaExistente.setDireccion(tienda.getDireccion());
            tiendaExistente.setTelefono(tienda.getTelefono());
            tiendaExistente.setCorreoElectronico(tienda.getCorreoElectronico());
            tiendaExistente.setFechaModificacion(LocalDateTime.now());
            return tiendaRepository.save(tiendaExistente);
        } else {
            return null; 
        }

    }

    public Tienda desactivar(Long id){
        Tienda tiendaExistente = tiendaRepository.findByIdTienda(id);
        if (tiendaExistente != null) {
            tiendaExistente.setEstado("INACTIVA");
            tiendaExistente.setFechaModificacion(LocalDateTime.now());
            return tiendaRepository.save(tiendaExistente);
        } else {
            return null; 
        }
    }

    public void eliminar(Long id){
        return tiendaRepository.deleteById(id);
    }
}
