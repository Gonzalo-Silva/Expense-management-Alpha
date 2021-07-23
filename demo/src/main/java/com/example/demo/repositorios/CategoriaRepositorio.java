package com.example.demo.repositorios;

import com.example.demo.entidades.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

    @Modifying
    @Query("UPDATE Categoria c SET c.nombre = :nombre WHERE c.idCat = :idCat")
    void modificar(@Param("idCat") Long idCat, @Param("nombre") String nombre);



}
