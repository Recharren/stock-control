package com.controlstock.galvamen.repositorios;

import com.controlstock.galvamen.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {


    @Query(value = "SELECT p FROM Producto p WHERE p.categoria = :categ")
    List<Producto> obtenerProductoPorCategoria(@Param("categ")int categ);

    @Query(value = "SELECT p FROM Producto p WHERE p.codigo = :codigo")
    Producto obtenerProductoPorCodigo(@Param("codigo")Integer codigo);
}
