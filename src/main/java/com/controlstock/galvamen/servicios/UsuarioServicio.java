package com.controlstock.galvamen.servicios;

import com.controlstock.galvamen.entidades.Usuario;
import com.controlstock.galvamen.excepciones.MiExcepcion;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    public void registrarUsuario(String nombre,String apellido, String email, String clave, String repetirClave, Integer rol) throws MiExcepcion {

        validarUsuarioNuevo(nombre, apellido, email,clave,repetirClave,rol);
        Usuario usuario;



    }

    private void validarUsuarioNuevo(String nombre, String apellido, String email, String clave, String repetirClave, Integer rol) throws MiExcepcion {

        if (nombre.isEmpty() || nombre == null){
            throw new MiExcepcion("El nombre no puede estar vacío o ser nulo");
        }

        if (apellido.isEmpty() || apellido == null){
            throw new MiExcepcion("El apellido no puede estar vacío o ser nulo");
        }

        if (email.isEmpty() || email == null){
            throw new MiExcepcion("El email no puede estar vacío o ser nulo");
        }

        if (clave.length()<6){
            throw new MiExcepcion("La clave debe tener como mínimo 6 caracteres");
        }

        if (!repetirClave.equals(clave)){
            throw new MiExcepcion("Las claves no son iguales");
        }

        if (rol<1 || rol>3){
            throw new MiExcepcion("El rol debe estar entre los valores 1 y 3");
        }

    }
}
