package com.controlstock.galvamen.servicios;

import com.controlstock.galvamen.entidades.Usuario;
import com.controlstock.galvamen.excepciones.MiExcepcion;
import com.controlstock.galvamen.repositorios.UsuarioRepositorio;
import com.controlstock.galvamen.roles.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public void registrarUsuario(String nombre,String apellido, String email, String clave, String repetirClave, Integer rol) throws MiExcepcion {

        validarUsuarioNuevo(nombre, apellido, email,clave,repetirClave,rol);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApeliido(apellido);
        usuario.setEmail(email);

        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(encriptada);

        switch (rol){
            case 1:
                usuario.setRol(Rol.USUARIO);
                break;
            case 2:
                usuario.setRol(Rol.JEFE);
                break;
            case 3:
                usuario.setRol(Rol.ADMIN);
        }

        usuarioRepositorio.save(usuario);

    }

    private void validarUsuarioNuevo(String nombre, String apellido, String email, String clave, String repetirClave, Integer rol) throws MiExcepcion {

        if (nombre.isEmpty() || nombre == null){
            throw new MiExcepcion("El nombre no puede estar vacío o ser nulo");
        }

        if (apellido.isEmpty() || apellido == null){
            throw new MiExcepcion("El apellido no puede estar vacío o ser nulo");
        }

        if (usuarioRepositorio.obtenerUsuarioPorEmail(email)!=null){
            throw new MiExcepcion("El email ya existe en la Base de datos. Pruebe con otro distinto.");
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

    // ------------Metodo de la implementacion UserDetailService encargado del logueo y la sesión del usuario.----------------
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = null;
        try {
            usuario = usuarioRepositorio.obtenerUsuarioPorEmail(email);
        } catch (Exception e) {
            throw new UsernameNotFoundException("No se encontro el usuario.");
        }

        if (usuario!=null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            // Creo una lista de permisos
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_"+usuario.getRol());
            permisos.add(p1);

            //Esto me permite guardar el OBJETO USUARIO(Cliente) LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            User user = new User(usuario.getEmail(), usuario.getClave(), permisos);
            return user;

        } else return null;
    }
}
