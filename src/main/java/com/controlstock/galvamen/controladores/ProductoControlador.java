package com.controlstock.galvamen.controladores;

import com.controlstock.galvamen.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;

    @PostMapping("/crearProducto")
    public String crearProducto(@RequestParam String nombre, @RequestParam Integer categoria,@RequestParam Integer codigo, @RequestParam Double precio){
        productoServicio.crearProducto(nombre,categoria,codigo,precio);
        return "redirect:/";
    }

    @GetMapping("/editarProducto/{codigo}")
    public String formularioEditarProducto (ModelMap modelo, @PathVariable Integer codigo){
        modelo.put("producto", productoServicio.buscarProdPorCodigo(codigo));
        return "editarProducto.html";
    }

    @PostMapping("/editarProducto")
    public String editarProducto(ModelMap modelo,@RequestParam String nombre, @RequestParam Integer categoria,@RequestParam Integer codigo, @RequestParam Double precio
    ,@RequestParam String autorUltimo, @RequestParam String sobrante,@RequestParam String cantIngresada, @RequestParam Integer stock,
                                 @RequestParam Date ultModif){
        productoServicio.editarProducto(nombre,categoria,codigo,precio,autorUltimo,sobrante,cantIngresada,stock,ultModif);
        return "redirect:/";
    }
}
