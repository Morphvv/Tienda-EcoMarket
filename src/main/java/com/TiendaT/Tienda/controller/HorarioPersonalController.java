package com.TiendaT.Tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TiendaT.Tienda.model.HorarioPersonal;
import com.TiendaT.Tienda.service.HorarioPersonalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/HorarioPersonal")
@CrossOrigin(origins = "*")
@Tag(name = "Horario personal", description = "Gestion de los horarios del personal")

public class HorarioPersonalController {

    @Autowired
    private HorarioPersonalService horarioPersonalService;

    //Crear horario del personal
    @Operation(summary = "Crear un horario del personal")
    @PostMapping("/crear")
    public HorarioPersonal crear(@RequestBody HorarioPersonal horarioPersonal){
        return horarioPersonalService.crear(horarioPersonal);
    }

    //Listar todos los horarios del personal
    @Operation(summary = "Listar todos los horarios del personal")
    @GetMapping("/listar")
    public List<HorarioPersonal> listar(){
        return horarioPersonalService.listar();
    }

    //Listar horarios del personal por id de asignacion
    @Operation(summary = "Buscar un horario por el id de la asignacion")
    @GetMapping("/listar/asignacion/{idAsignacion}")
    public List<HorarioPersonal> listarPorAsignacion(@PathVariable Long idAsignacion){
        return horarioPersonalService.listarPorAsignacion(idAsignacion);
    }

    //Modificar horario del personal por id
    @Operation(summary = "Modificar un horario")
    @PutMapping("/modificar/{idHorarioPersonal}")
    public HorarioPersonal modificar(@PathVariable Long idHorarioPersonal, @RequestBody HorarioPersonal horarioPersonal){
        return horarioPersonalService.modificar(idHorarioPersonal, horarioPersonal);
    }

    //Desactivar horario del personal por id
    @Operation(summary = "Desactivar un horario del personal")
    @PutMapping("/desactivar/{idHorarioPersonal}")
    public HorarioPersonal desactivar(@PathVariable Long idHorarioPersonal){
        return horarioPersonalService.desactivar(idHorarioPersonal);
    }

    //Eliminar horario del personal por id
    @Operation(summary = "Eliminar un horario del personal")
    @DeleteMapping("/eliminar/{idHorarioPersonal}")
    public void eliminar(@PathVariable Long idHorarioPersonal){
        horarioPersonalService.eliminar(idHorarioPersonal);
    }

}
