package com.controlstock.galvamen.servicios;

import com.controlstock.galvamen.entidades.Producto;
import com.controlstock.galvamen.repositorios.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductoServicio {

    @Autowired
    ProductoRepositorio productoRepositorio;

    //------------- obtiene lista de productos segun categoria ---------------------
    public List<Producto> listadoProductos(int categ) {
        List<Producto> Productos = productoRepositorio.obtenerProductoPorCategoria(categ);
        if (Productos != null){
            return Productos;
        } else return null;
    }

    //------------- crea un producto nuevo ------------------
    public void crearProducto(String nombre, Integer categoria,Integer codigo, Double precio) {
        Producto producto = new Producto() ;
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setCodigo(codigo);
        producto.setPrecio(precio);
        producto.setAutorUltimo("origen");
        producto.setSobrante("sin sobrante");
        producto.setCantIngresada("sin datos");
        producto.setStock(0);
        producto.setUltModif(new Date());
        productoRepositorio.save(producto);
    }

    public void editarProducto(String nombre, Integer categoria, Integer codigo, Double precio, String autorUltimo, String sobrante, String cantIngresada, Integer stock, Date ultModif) {
        Producto producto = productoRepositorio.obtenerProductoPorCodigo(codigo);
        producto.setNombre(nombre);
        producto.setCategoria(categoria);

    }

    public Producto buscarProdPorCodigo(Integer codigo) {
        return productoRepositorio.obtenerProductoPorCodigo(codigo);
    }
}
