package com.TiendaT.Tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaT.Tienda.model.ProductoAsociadoTienda;
import com.TiendaT.Tienda.repository.ProductoAsociadoTiendaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class ProductoAsociadoTiendaService {

    @Autowired
    private ProductoAsociadoTiendaRepository productoAsociadoTiendaRepository;

    //Crear un producto asociado a la tienda
    public ProductoAsociadoTienda crear(ProductoAsociadoTienda productoAsociadoTienda){
        productoAsociadoTienda.setVisibleEnTienda(true);
        return productoAsociadoTiendaRepository.save(productoAsociadoTienda);
    }

    //Listar todos los productos asociados a la tienda
    public List <ProductoAsociadoTienda> listar(){
        return productoAsociadoTiendaRepository.findAll();
    }

    //Listar productos asociados a la tienda por id de tienda
    public List <ProductoAsociadoTienda> listarPorTienda(Long idTienda){
        return productoAsociadoTiendaRepository.findByIdTienda(idTienda);
    }

    //Modificar producto asociado a la tienda por el id 
    public ProductoAsociadoTienda modificar(Long id, ProductoAsociadoTienda productoAsociadoTienda){
        ProductoAsociadoTienda existente = productoAsociadoTiendaRepository.findByIdProductoAsociadoTienda(id).orElse(null);
        if (existente != null){
            existente.setNombreProducto(productoAsociadoTienda.getNombreProducto());
            existente.setVisibleEnTienda(productoAsociadoTienda.getVisibleEnTienda());
            return productoAsociadoTiendaRepository.save(existente);
        }
        return null;
    }

    //Ocultar un producto asociado a la tienda por el id 
    public ProductoAsociadoTienda ocultarProducto(Long id){
        ProductoAsociadoTienda existente = productoAsociadoTiendaRepository.findByIdProductoAsociadoTienda(id).orElse(null);
        if (existente != null){
            existente.setVisibleEnTienda(false);
            return productoAsociadoTiendaRepository.save(existente);
        }
        return null;
    }

    //Eliminar producto asociado a la tienda por id
    public void eliminar(Long id){
        productoAsociadoTiendaRepository.deleteById(id);
    }

    
}
