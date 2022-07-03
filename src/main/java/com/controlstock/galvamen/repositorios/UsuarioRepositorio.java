package com.controlstock.galvamen.repositorios;

import com.controlstock.galvamen.entidades.Producto;
import com.controlstock.galvamen.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario obtenerUsuarioPorEmail(@Param("email")String email);

}
