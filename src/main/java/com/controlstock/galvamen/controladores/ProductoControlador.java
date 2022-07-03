package com.controlstock.galvamen.controladores;

import com.controlstock.galvamen.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/producto")
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;
// -----------------------------------------------------------------------------------------------------
    @GetMapping("/formularioProducto")
    public String formularioProducto (){
        return "formularioProducto.html";
    }
    // -------------------------------------------------------------------------------------------------
    @PostMapping("/crearProducto")
    public String crearProducto(@RequestParam String nombre, @RequestParam Integer categoria,@RequestParam Integer codigo, @RequestParam Double precio){
        productoServicio.crearProducto(nombre,categoria,codigo,precio);
        return "redirect:/moduloStock";
    }
    // -------------------------------------------------------------------------------------------------
    @GetMapping("/editarProducto/{codigo}")
    public String formularioEditarProducto (ModelMap modelo, @PathVariable Integer codigo){
        modelo.put("producto", productoServicio.buscarProdPorCodigo(codigo));
        return "editarProducto.html";
    }

    @PostMapping("/editarProducto")
    public String editarProducto(ModelMap modelo,@RequestParam String nombre, @RequestParam Integer categoria,@RequestParam Integer codigo, @RequestParam Double precio
    ,@RequestParam String sobrante,@RequestParam String cantIngresada,@RequestParam Long autorUltimo,  @RequestParam Integer stock,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ultModif){
        productoServicio.editarProducto(nombre,categoria,codigo,precio,autorUltimo,sobrante,cantIngresada,stock,ultModif);
        return "redirect:/moduloStock";
    }
    // -------------------------------------------------------------------------------------------------
    @PostMapping("/agregarStock")
    public String agregarStock(@RequestParam Long idSesionAgre, @RequestParam Integer codigoProdAgre, @RequestParam Integer cantAgre,
                               @RequestParam String sobranteAgre){
        productoServicio.agregarStock(idSesionAgre, codigoProdAgre, cantAgre, sobranteAgre);
        return "redirect:/moduloStock";
    }

    @PostMapping("/consumirStock")
    public String consumirStock(@RequestParam Long idSesionCons, @RequestParam Integer codigoProdCons, @RequestParam Integer cantCons,
                               @RequestParam String sobranteCons){
        productoServicio.consumirStock(idSesionCons, codigoProdCons, cantCons, sobranteCons);
        return "redirect:/moduloStock";
    }
    // --------------------------------------------------------------------------------------------------
}
