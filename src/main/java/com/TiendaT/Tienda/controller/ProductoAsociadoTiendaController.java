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

import com.TiendaT.Tienda.model.ProductoAsociadoTienda;
import com.TiendaT.Tienda.service.ProductoAsociadoTiendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/ProductoAsociadoTienda")
@CrossOrigin(origins = "*")
@Tag(name = "Producto asociado a la tienda", description = "Gestion de los productos asociados a la tienda")

public class ProductoAsociadoTiendaController {

    @Autowired
    private ProductoAsociadoTiendaService productoAsociadoTiendaService;

    //Crear producto asociado
    @Operation(summary = "Crear un producto asociado")
    @PostMapping("/crear")
    public ProductoAsociadoTienda crearProductoAsociado (@Valid @RequestBody ProductoAsociadoTienda productoAsociadoTienda){
        return productoAsociadoTiendaService.crearProductoAsociado(productoAsociadoTienda);
    }

    //Listar todos los produtos asociados por el id de la tienda
    @Operation(summary = "Listar todos los productos asociado a esa tienda")
    @GetMapping("/listar")
    public List<ProductoAsociadoTienda> listarTiendaProducto(){
        return productoAsociadoTiendaService.listarTiendaProducto();
    }
    
    //Buscar producto asociado por idTienda
    @Operation(summary = "Buscar un producto por tienda")
    @GetMapping("/listar/tienda/{idTienda}")
    public List<ProductoAsociadoTienda> listarPorTiendaProducto(@PathVariable Long idTienda){
        return productoAsociadoTiendaService.listarPorTiendaProducto(idTienda);
    }

    //Modificar producto asociado
    @Operation(summary = "Modificar un producto")
    @PutMapping("/modificar/{idProductoAsociado}")
    public ProductoAsociadoTienda modificarProductoTienda(@PathVariable Long idProductoAsociado, @RequestBody ProductoAsociadoTienda productoAsociadoTienda){
        return productoAsociadoTiendaService.modificarProductoTienda(idProductoAsociado, productoAsociadoTienda);
    }

    //Ocultar producto 
    @Operation(summary = "Ocultar un prodcuto asociado")
    @PutMapping("/ocultar/{idProductoAsociado}")
    public ProductoAsociadoTienda ocultarProductoAsociadoTienda(@PathVariable Long idProductoAsociado){
        return productoAsociadoTiendaService.ocultarProductoAsociadoTienda(idProductoAsociado);
    }

    //Eliminar producto asociado
    @Operation(summary = "Eliminar un producto asociado")
    @DeleteMapping("/eliminar/{idProductoAsociado}")
    public void eliminarProductoAsociadoTienda(@PathVariable Long idProductoAsociado){
        productoAsociadoTiendaService.eliminarProductoAsociadoTienda(idProductoAsociado);
    }
        
}
