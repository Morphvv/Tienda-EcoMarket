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

import com.TiendaT.Tienda.model.AsignacionPersonal;
import com.TiendaT.Tienda.service.AsignacionPersonalService;

@RestController
@RequestMapping("api/v1/AsignacionPersonal")
@CrossOrigin(origins = "*")

public class AsignacionPersonalController {

    @Autowired
    private AsignacionPersonalService asignacionPersonalService;

    //Crear asignacion
    @PostMapping("/crear")
    public AsignacionPersonal crearAsignacionP(@RequestBody AsignacionPersonal asignacionPersonal){
        return asignacionPersonalService.crearAsignacionP(asignacionPersonal);
    }

    //Listar todas las asignaciones
    @GetMapping("/listar")
    public List<AsignacionPersonal> listarAsignacionPersonal(){
        return asignacionPersonalService.listarAsignacionPersonal();
    }

    //Buscar una asignacion por id de tienda
    @GetMapping("/listar/tienda/{idTienda}")
    public List<AsignacionPersonal> listarPorTiendaAsignacionP(@PathVariable Long idTienda){
        return asignacionPersonalService.listarPorTiendaAsignacionP(idTienda);
    }

    //Modificar una asignacion por id de la asignacion
    @PutMapping("/modificar/{idAsignacion}")
    public AsignacionPersonal modificarAsignacionP(@PathVariable Long idAsignacion, @RequestBody AsignacionPersonal asignacionPersonal){
        return asignacionPersonalService.modificarAsignacionP(idAsignacion, asignacionPersonal);
    }

    //Desactivar una asignacion por su id
    @PutMapping("/desactivar/{idAsignacion}")
    public AsignacionPersonal desactivarAsignacionP(@PathVariable Long idAsignacion){
        return asignacionPersonalService.desactivarAsignacionP(idAsignacion);
    }

    //Eliminar asignacion
    @DeleteMapping("/eliminar/{idAsignacion}")
    public void eliminarAsignacionP(@PathVariable Long idAsignacion){
        asignacionPersonalService.eliminarAsignacionP(idAsignacion);
    }
}
