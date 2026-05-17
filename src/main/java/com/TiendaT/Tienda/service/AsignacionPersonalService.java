package com.TiendaT.Tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaT.Tienda.model.AsignacionPersonal;
import com.TiendaT.Tienda.repository.AsignacionPersonalRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class AsignacionPersonalService {

    @Autowired
    private AsignacionPersonalRepository asignacionPersonalRepository;

    //Crear una asignacion de un personal para la tienda
    public AsignacionPersonal crearAsignacionPersonal(AsignacionPersonal asignacionPersonal){
        asignacionPersonal.setEstadoAsignacion("ACTIVA");
        return asignacionPersonalRepository.save(asignacionPersonal);
    }

    //Listar todos las asignaciones del personal de la tienda 
    public List <AsignacionPersonal> listarAsignacionPersonal(){
        return asignacionPersonalRepository.findAll();
    }

    //Listar todas las asignaciones del personal de la tienda por id de tienda
    public List <AsignacionPersonal> listarPorTiendaAsignacion(Long idTienda){
        return asignacionPersonalRepository.findByIdTienda(idTienda);
    }

    //Modificar la asignacion del personal de la tienda por su id 
    public AsignacionPersonal modificarAsignacion(Long idAsignacionPersonal, AsignacionPersonal asignacionPersonal){
        AsignacionPersonal existente = asignacionPersonalRepository.findByIdAsignacion(idAsignacionPersonal).orElse(null);
        if (existente != null){
            existente.setNombreEmpleado(asignacionPersonal.getNombreEmpleado());
            existente.setCargo(asignacionPersonal.getCargo());
            existente.setFechaInicio(asignacionPersonal.getFechaInicio());
            existente.setFechaTermino(asignacionPersonal.getFechaTermino());
            return asignacionPersonalRepository.save(existente);
        }
        return null;
    }

    //Desactivar la asignacion del personal de la tienda por su id 
    public AsignacionPersonal desactivarAsignacion(Long id){
        AsignacionPersonal existente = asignacionPersonalRepository.findByIdAsignacion(idAsignacion).orElse(null);
        if (existente != null){
            existente.setEstadoAsignacion("INACTIVA");
            return asignacionPersonalRepository.save(existente);
        }
        return null;
    }

    //Eliminar la asignacion del personal de la tienda por su id
    public void eliminarAsignacion(Long id){
        asignacionPersonalRepository.deleteById(id);
    }

}
