package com.example.demo.repositorios;

import java.util.*;
import com.example.demo.entidades.Categoria;
import com.example.demo.entidades.FormasPago;
import com.example.demo.entidades.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepositorio extends JpaRepository<Ingreso, Long>{


    @Modifying
    @Query("UPDATE Ingreso i SET i.descripcion = :descripcion, i.monto = :monto, i.fecha =:fecha, i.nota = :nota, i.categoria = :categoria, i.formasPago = :formasPago WHERE i.id = :id")
    void modificar(@Param("id") Long id, @Param("descripcion") String descripcion, @Param("fecha") Date fecha, @Param("nota") String nota, @Param("monto") Long monto,
    @Param("categoria") Categoria categoria, @Param("formasPago") FormasPago formasPago);

    @Query("SELECT sum(i.monto) FROM Ingreso i")
    Long ingresosTodos();

    @Query("SELECT sum(i.monto) FROM Ingreso i where i.fecha >= :desde and i.fecha <= :hasta")
    Long ingresosTodos(@Param("desde") Date desde,@Param("hasta") Date hasta);


    @Query("SELECT new com.example.demo.entidades.Ingreso(sum(i.monto), i.categoria) FROM Ingreso i group by i.categoria")
    List<Ingreso> ingresosCategoria();

    @Query("SELECT new com.example.demo.entidades.Ingreso(sum(i.monto), i.categoria) FROM Ingreso i where i.fecha >= :desde and i.fecha <= :hasta group by i.categoria ")
    List<Ingreso> ingresosCategoria(@Param("desde") Date desde,@Param("hasta") Date hasta);


}