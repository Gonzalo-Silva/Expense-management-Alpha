package com.example.demo.repositorios;

import java.util.*;
import com.example.demo.entidades.Categoria;
import com.example.demo.entidades.FormasPago;
import com.example.demo.entidades.Egreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EgresoRepositorio extends JpaRepository<Egreso, Long> {

    @Modifying
    @Query("UPDATE Egreso e SET e.descripcion = :descripcion, e.monto = :monto, e.fecha =:fecha, e.nota = :nota, e.categoria = :categoria, e.formasPago = :formasPago WHERE e.id = :id")
    void modificar(@Param("id") Long id, @Param("descripcion") String descripcion, @Param("fecha") Date fecha, @Param("nota") String nota, @Param("monto") Long monto,
    @Param("categoria") Categoria categoria, @Param("formasPago") FormasPago formasPago);

    @Query("SELECT sum(e.monto) FROM Egreso e")
    Long egresosTodos();

    @Query("SELECT sum(e.monto) FROM Egreso e where e.fecha >= :desde and e.fecha <= :hasta")
    Long egresosTodos(@Param("desde") Date desde,@Param("hasta") Date hasta);


    @Query("SELECT new com.example.demo.entidades.Egreso(sum(e.monto), e.categoria) FROM Egreso e group by e.categoria")
    List<Egreso> egresosCategoria();

    @Query("SELECT new com.example.demo.entidades.Egreso(sum(e.monto), e.categoria) FROM Egreso e where e.fecha >= :desde and e.fecha <= :hasta group by e.categoria ")
    List<Egreso> egresosCategoria(@Param("desde") Date desde,@Param("hasta") Date hasta);

}
/*ORM
SQL IN*/