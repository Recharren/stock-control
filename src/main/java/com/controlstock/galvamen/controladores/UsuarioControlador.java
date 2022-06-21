package com.controlstock.galvamen.controladores;

import com.controlstock.galvamen.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/registroUsuario")
    public String registroUsuario(ModelMap modelo){
        return "registroUsuario.html";
    }

    @PostMapping("/registrarUsuario")
    public String registrarUsuario(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email,
            @RequestParam String clave,@RequestParam String repetirClave, @RequestParam Integer rol){
        usuarioServicio.registrarUsuario(nombre, apellido, email, clave,repetirClave, rol);
    }
}
