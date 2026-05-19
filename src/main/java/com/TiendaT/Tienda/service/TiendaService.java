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

    public Tienda crearTienda(Tienda tienda){
        tienda.setFechaCreacion(LocalDateTime.now());
        tienda.setEstado("ACTIVA");
        return tiendaRepository.save(tienda);
    }

    public List<Tienda> listarTiendas(){
        return tiendaRepository.findAll();
    }

    public Tienda buscarTiendaPorId(Long idTienda){
        return tiendaRepository.findById(idTienda).orElse(null);
    }

    public Tienda modificarTienda(Long id, Tienda tienda){
        Tienda tiendaExistente = tiendaRepository.findById(id).orElse(null);
        if (tiendaExistente != null) {
            tiendaExistente.setNombre(tienda.getNombre());
            tiendaExistente.setDireccion(tienda.getDireccion());
            tiendaExistente.setTelefono(tienda.getTelefono());
            return tiendaRepository.save(tiendaExistente);
        } else {
            return null;
        }

    }

    public Tienda desactivarTienda(Long id){
        Tienda tiendaExistente = tiendaRepository.findById(id).orElse(null);
        if (tiendaExistente != null) {
            tiendaExistente.setEstado("INACTIVA");
            return tiendaRepository.save(tiendaExistente);
        } else {
            return null;
        }
    }

    public void eliminarTienda(Long idTienda){
        tiendaRepository.deleteById(idTienda);
    }
}
