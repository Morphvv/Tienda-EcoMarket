package com.TiendaT.Tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaT.Tienda.model.HorarioTienda;
import com.TiendaT.Tienda.repository.HorarioTiendaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class HorarioTiendaService {

    @Autowired
    private HorarioTiendaRepository horarioTiendaRepository;

    //Para crear el horario de la tienda
    public HorarioTienda crearHorarioTienda(HorarioTienda horarioTienda){
        horarioTienda.setActivo(true);
        return horarioTiendaRepository.save(horarioTienda);
    }

    //Para poder listar todos los horarios creados
    public List <HorarioTienda> listarHorarioTienda(){
        return horarioTiendaRepository.findAll();
    }

    //Buscar un horario en especifico por el id
    public List <HorarioTienda> listarPorTiendaHorario(Long idTienda){
        return horarioTiendaRepository.findByIdTienda(idTienda)
    }

    //Para modificar el horario de tienda por su id
    public HorarioTienda modificarHorarioTienda(Long id, HorarioTienda horarioTienda){
        HorarioTienda existente = horarioTiendaRepository.findByIdHorarioTienda(id);
        if (existente != null) {
            existente.setDiaSemana(horarioTienda.getDiaSemana());
            existente.setHoraInicio(horarioTienda.getHoraInicio());
            existente.setHoraTermino(horarioTienda.getHoraTermino());
            existente.setTurno(horarioTienda.getTurno());
            return horarioTiendaRepository.save(existente);
        } else {
            return null; 
        }
    }

    //Para desactovar un horario en especifico por su id
    public HorarioTienda desactivarHorarioTienda(Long idHorarioTienda){
        HorarioTienda existente = horarioTiendaRepository.findByIdHorarioTienda(id).orElse(null);
        if (existente != null){
            existente.setActivo(false);
            return horarioTiendaRepository.save(existente);
        }
        return null;
    }


    //Para eliminar un horario por el id
    public void eliminarHorarioTienda(Long idHorarioTienda){
        HorarioTiendaRepository.deleteById(idHorarioTienda);
    }


    
}
