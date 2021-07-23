
package com.example.demo.repositorios;

import com.example.demo.entidades.FormasPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormasPagoRepositorio extends JpaRepository<FormasPago, Long> {

    @Modifying
    @Query("UPDATE FormasPago f SET f.tipo = :tipo WHERE f.idPago = :idPago")
    void modificar(@Param("idPago") Long idPago, @Param("tipo") String tipo);


}
