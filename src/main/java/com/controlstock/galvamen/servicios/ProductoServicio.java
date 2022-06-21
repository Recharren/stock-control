package com.controlstock.galvamen.servicios;

import com.controlstock.galvamen.entidades.Producto;
import com.controlstock.galvamen.repositorios.ProductoRepositorio;
import com.controlstock.galvamen.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductoServicio {

    @Autowired
    ProductoRepositorio productoRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    //------------- Obtiene lista de productos segun categoria ---------------------

    public List<Producto> listadoProductos(int categ) {
        List<Producto> Productos = productoRepositorio.obtenerProductoPorCategoria(categ);
        if (Productos != null){
            return Productos;
        } else return null;
    }

    //------------- Crea un producto nuevo ---------------------------------

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
// ---------------------- Edita un producto -----------------------------------

    public void editarProducto(String nombre, Integer categoria, Integer codigo, Double precio, String autorUltimo, String sobrante, String cantIngresada, Integer stock, Date ultModif) {
        Producto producto = productoRepositorio.obtenerProductoPorCodigo(codigo);
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setCodigo(codigo);
        producto.setPrecio(precio);
        producto.setAutorUltimo(autorUltimo);
        producto.setSobrante(sobrante);
        producto.setStock(stock);
        producto.setCantIngresada(cantIngresada);
        producto.setUltModif(ultModif);

        productoRepositorio.save(producto);

    }

    // ---------------------Busca un producto por codigo ---------------------------------------

    public Producto buscarProdPorCodigo(Integer codigo) {
        return productoRepositorio.obtenerProductoPorCodigo(codigo);
    }

    // -------------------- Agrega Stock --------------------------------------------------

    public void agregarStock(Long idSesionAgre, Integer codigoProdAgre, Integer cantAgre, String sobranteAgre) {
        Producto producto = productoRepositorio.obtenerProductoPorCodigo(codigoProdAgre);
        producto.setStock(producto.getStock()+cantAgre);
        producto.setSobrante(sobranteAgre);
        producto.setAutorUltimo(usuarioRepositorio.getById(idSesionAgre).getNombre());
        producto.setUltModif(new Date());
        productoRepositorio.save(producto);
    }

    // -------------------- Consumir Stock --------------------------------------------------

    public void consumirStock(Long idSesionCons, Integer codigoProdCons, Integer cantCons, String sobranteCons) {
        Producto producto = productoRepositorio.obtenerProductoPorCodigo(codigoProdCons);
        producto.setStock(producto.getStock()-cantCons);
        producto.setSobrante(sobranteCons);
        producto.setAutorUltimo(usuarioRepositorio.getById(idSesionCons).getNombre());
        producto.setUltModif(new Date());
        productoRepositorio.save(producto);
    }
}

