package com.controlstock.galvamen.restControlador;

import com.controlstock.galvamen.entidades.Producto;
import com.controlstock.galvamen.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productoRest")
public class ProductoRestControlador {

    @Autowired
    ProductoServicio productoServicio;

    @GetMapping("/productos/{categ}")
    public List<Producto> listaDeProductos(@PathVariable Integer categ){

        try {
          return productoServicio.listadoProductos(categ);
        }catch (Exception ex){
            return null;
        }
    }
}
