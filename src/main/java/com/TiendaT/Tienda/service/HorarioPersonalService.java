package com.TiendaT.Tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaT.Tienda.model.HorarioPersonal;
import com.TiendaT.Tienda.repository.HorarioPersonalRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class HorarioPersonalService {

    @Autowired
    private HorarioPersonalRepository horarioPersonalRepository;

    //Crear el horario del personal de la tienda
    public HorarioPersonal crear(HorarioPersonal horarioPersonal){
        horarioPersonal.setActivo(true);
        return horarioPersonalRepository.save(horarioPersonal);
    }

    //Listar todos los horarios del personal de la tienda
    public List<HorarioPersonal> listar(){
        return horarioPersonalRepository.findAll();
    }

    //Listar los horarios del personal por id de asignacion
    public List<HorarioPersonal> listarPorAsignacion(Long idAsignacion){
        return horarioPersonalRepository.findByIdAsignacion(idAsignacion);
    }

    //Modificar el horario del personal de la tienda por el id
    public HorarioPersonal modificar(Long id, HorarioPersonal horarioPersonal){
        HorarioPersonal existente = horarioPersonalRepository.findById(id).orElse(null);
        if (existente != null){
            existente.setDiaSemana(horarioPersonal.getDiaSemana());
            existente.setHoraInicio(horarioPersonal.getHoraInicio());
            existente.setHoraTermino(horarioPersonal.getHoraTermino());
            existente.setTurno(horarioPersonal.getTurno());
            return horarioPersonalRepository.save(existente);
        }
        return null;
    }

    //Desactivar el horario del personal de la tienda por el id
    public HorarioPersonal desactivar(Long id){
        HorarioPersonal existente = horarioPersonalRepository.findById(id).orElse(null);
        if (existente != null){
            existente.setActivo(false);
            return horarioPersonalRepository.save(existente);
        }
        return null;
    }

    //Eliminar el horario del personal de la tienda por id
    public void eliminar(Long id){
        horarioPersonalRepository.deleteById(id);
    }

}
