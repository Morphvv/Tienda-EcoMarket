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

import com.TiendaT.Tienda.model.Tienda;
import com.TiendaT.Tienda.service.TiendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/tienda")
@CrossOrigin(origins = "*")
@Tag(name = "Tienda", description = "Gestion de tienda")

public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    //Crear una nueva tienda
    @Operation(summary = "Crear una nueva tienda")
    @PostMapping("/crear")
    public Tienda crearTienda(@Valid @RequestBody Tienda tienda){
        return tiendaService.crearTienda(tienda);
    }

    //Listar las tiendas
    @Operation(summary = "Listar todas las tiendas")
    @GetMapping("/listar")
    public List <Tienda> listarTiendas(){
        return tiendaService.listarTiendas();
    }

    //Buscar tienda por id 
    @Operation(summary = "Buscar una tienda")
    @GetMapping("/buscar/{idTienda}")
    public Tienda buscarTiendaPorId(@PathVariable Long idTienda){
        return tiendaService.buscarTiendaPorId(idTienda);
    }

    //Modificar tienda
    @Operation(summary = "Modificar una tienda por su id")
    @PutMapping("/modificar/{idTienda}")
    public Tienda modificarTienda(@PathVariable Long idTienda, @RequestBody Tienda tienda){
        return tiendaService.modificarTienda(idTienda, tienda);
    }

    //Desactivar tienda
    @Operation(summary = "Desactivar una tienda")
    @PutMapping("/desactivar/{idTienda}")
    public Tienda desactivarTienda(@PathVariable Long idTienda){
        return tiendaService.desactivarTienda(idTienda);
    }

    //Eliminar tienda
    @Operation(summary = "Eliminar una tienda")
    @DeleteMapping("/eliminar/{idTienda}")
    public void eliminarTienda(@PathVariable Long idTienda){
        tiendaService.eliminarTienda(idTienda);
    }
    
}
