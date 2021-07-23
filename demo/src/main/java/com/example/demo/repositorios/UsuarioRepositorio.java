package com.example.demo.repositorios;

import com.example.demo.entidades.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    
    @Query("SELECT u FROM Usuario u WHERE u.mail = :mail")
    Usuario buscarPorNombreDeUsuario(@Param("mail") String mail);

}