package com.controlstock.galvamen;

import com.controlstock.galvamen.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GalvamenApplication {

	@Autowired
	UsuarioServicio usuarioServicio;

	public static void main(String[] args) {

		SpringApplication.run(GalvamenApplication.class, args);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { // Encriptamos la clave del usuario
		auth
				.userDetailsService(usuarioServicio)
				.passwordEncoder(new BCryptPasswordEncoder());
	}
}
