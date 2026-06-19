package com.TiendaT.Tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TiendaT.Tienda.model.AsignacionPersonal;
import com.TiendaT.Tienda.service.AsignacionPersonalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/AsignacionPersonal")
@CrossOrigin(origins = "*")
@Tag(name = "Asignacion Personal", description = "(Gestion de asignaciones de personal")

public class AsignacionPersonalController {

    @Autowired
    private AsignacionPersonalService asignacionPersonalService;

    //Crear asignacion
    @Operation(summary = "Crear una asignacion")
    @PostMapping("/crear")
    public AsignacionPersonal crearAsignacionP(@Valid @RequestBody AsignacionPersonal asignacionPersonal){
        return asignacionPersonalService.crearAsignacionP(asignacionPersonal);
    }

    //Listar todas las asignaciones
    @Operation(summary = "Listar todas las asignaciones del personal")
    @GetMapping("/listar")
    public List<AsignacionPersonal> listarAsignacionPersonal(){
        return asignacionPersonalService.listarAsignacionPersonal();
    }

    //Buscar una asignacion por id de tienda
    @Operation(summary = "Buscar una asignacion por id de la tienda")
    @GetMapping("/listar/tienda/{idTienda}")
    public List<AsignacionPersonal> listarPorTiendaAsignacionP(@PathVariable Long idTienda){
        return asignacionPersonalService.listarPorTiendaAsignacionP(idTienda);
    }

    //Modificar una asignacion por id de la asignacion
    @Operation(summary = ("Modificar una asignacion"))
    @PutMapping("/modificar/{idAsignacion}")
    public AsignacionPersonal modificarAsignacionP(@PathVariable Long idAsignacion, @RequestBody AsignacionPersonal asignacionPersonal){
        return asignacionPersonalService.modificarAsignacionP(idAsignacion, asignacionPersonal);
    }

    //Desactivar una asignacion por su id
    @Operation(summary = "Desactivar una asignacion")
    @PutMapping("/desactivar/{idAsignacion}")
    public AsignacionPersonal desactivarAsignacionP(@PathVariable Long idAsignacion){
        return asignacionPersonalService.desactivarAsignacionP(idAsignacion);
    }

    //Eliminar asignacion
    @Operation(summary = "Eliminar una asignacion")
    @DeleteMapping("/eliminar/{idAsignacion}")
    public void eliminarAsignacionP(@PathVariable Long idAsignacion){
        asignacionPersonalService.eliminarAsignacionP(idAsignacion);
    }
}
