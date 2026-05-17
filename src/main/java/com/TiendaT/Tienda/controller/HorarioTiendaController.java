package com.TiendaT.Tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TiendaT.Tienda.model.HorarioTienda;
import com.TiendaT.Tienda.service.HorarioTiendaService;

@RestController
@RequestMapping("api/v1/HorarioTienda")
@CrossOrigin(origins = "*")

public class HorarioTiendaController {

    @Autowired
    private HorarioTiendaService horarioTiendaService;

    //Crear horario de tienda
    @PutMapping("/crear")
    public HorarioTienda crearHorarioTienda(@RequestBody HorarioTienda horarioTienda){
        return horarioTiendaService.crearHorarioTienda(horarioTienda);
    }

    //Listar TODOS los horarios de tienda
    @GetMapping("/listar")
    public List<HorarioTienda> listarHorarioTienda(){
        return horarioTiendaService.listarHorarioTienda();
    }

    //Buscar un horario de tienda por ID DE TIENDA
    @GetMapping("/listar/tienda/{idTienda}")
    public List <HorarioTienda> listarPorTiendaHorario(@PathVariable Long idTienda){
        return horarioTiendaService.listarPorTiendaHorario(idTienda);
    }

    //Modificar horario de tienda
    @PutMapping("/modificar/{idHorarioTienda}")
    public HorarioTienda modificarHorarioTienda(@PathVariable Long idHorarioTienda, @RequestBody HorarioTienda horarioTienda){
        return horarioTiendaService.modificarHorarioTienda(idHorarioTienda, horarioTienda);
    }

    //Desactivar horario de tienda
    @PutMapping("/desactivar/{idHorarioTienda}")
    public HorarioTienda desactivarHorarioTienda(@PathVariable Long idHorarioTienda){
        return horarioTiendaService.desactivarHorarioTienda(idHorarioTienda);
    }

    //Eliminar horario de tienda
    public void eliminarHorarioTienda (@PathVariable Long idHorarioTienda){
        horarioTiendaService.eliminarHorarioTienda(idHorarioTienda);
    }

    
}
