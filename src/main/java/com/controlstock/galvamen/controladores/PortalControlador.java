package com.controlstock.galvamen.controladores;

import com.controlstock.galvamen.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalControlador {

    @Autowired
    ProductoServicio productoServicio;

    @GetMapping("/")
    public String index(ModelMap modelo){
        modelo.put("planchuelas", productoServicio.listadoProductos(1));
        modelo.put("angulos", productoServicio.listadoProductos(2));
        modelo.put("redondos", productoServicio.listadoProductos(3));
        modelo.put("upns", productoServicio.listadoProductos(4));
        modelo.put("canios", productoServicio.listadoProductos(5));
        modelo.put("perfilTs", productoServicio.listadoProductos(6));
        return "index.html";
    }

    @GetMapping("/formularioProducto")
    public String formularioProducto (){
        return "formularioProducto.html";
    }
}
