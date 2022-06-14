package com.controlstock.galvamen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GalvamenApplication {

	//@Autowired
	//UsuarioServicio usuarioServicio;

	public static void main(String[] args) {

		SpringApplication.run(GalvamenApplication.class, args);
	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { // Encriptamos la clave del usuario
//		auth
//				.userDetailsService(usuarioServicio)
//				.passwordEncoder(new BCryptPasswordEncoder());
//	}
}
