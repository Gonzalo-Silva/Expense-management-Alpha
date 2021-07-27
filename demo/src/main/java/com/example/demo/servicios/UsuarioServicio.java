package com.example.demo.servicios;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.entidades.Usuario;
import com.example.demo.repositorios.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired(required = true) // Declarar atributo "BCrypt" para encriptar
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void crear(String nombre, String apellido, Long dni, String mail, String contrasena) {
        Usuario u = new Usuario();

        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setDni(dni);
        u.setMail(mail);
        u.setContrasena(encoder.encode(contrasena)); // encriptar
        // u.setContrasena(contrasena);
        usuarioRepositorio.save(u);

    }

    public void IniciarSesion(String mail, String contrasena) throws Exception {
        // Validar(mail, contrasena);

        Usuario u = new Usuario();

        u.setMail(mail);
        u.setContrasena(contrasena);

        usuarioRepositorio.save(u);

    }

    public void Modificar(Long id, String mail, String contrasena) throws Exception {
        // Validar( mail, contrasena);
        Optional<Usuario> request = usuarioRepositorio.findById(id);
        if (request.isPresent()) {
            Usuario u = request.get();

            u.setMail(mail);

            usuarioRepositorio.save(u);
        } else {
            throw new Exception("No se encontró el usuario solicitado.");
        }
    }

    public void Eliminar(Long id) throws Exception {
        Optional<Usuario> request = usuarioRepositorio.findById(id);
        if (request.isPresent()) {
            Usuario u = request.get();
            u.setBaja(new Date());
            ;

            usuarioRepositorio.save(u);
        } else {
            throw new Exception("No se encontró el usuario solicitado.");
        }
    }

    public void Validar(String mail, String contrasena) throws Exception {

        if (mail == null || mail.isEmpty()) {
            throw new Exception("El e-mail del usuario no puede ser nulo.");
        }
        if (contrasena == null || contrasena.isEmpty() || contrasena.length() <= 8) {
            throw new Exception("La contraseña del usuario no puede ser nula o mayor de 8 dígitos.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorNombreDeUsuario(mail);
        if (usuario == null) {
            throw new UsernameNotFoundException("No hay ningun usuario con username" + mail);

        }

        return new org.springframework.security.core.userdetails.User(usuario.getMail(), usuario.getContrasena(),
                Collections.emptyList());
    }

}
