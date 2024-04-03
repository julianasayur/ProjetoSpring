package com.example.demo.modulos.emprestimo.repository;

import com.example.demo.modulos.emprestimo.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Transactional(readOnly = true)
    @Query(value = "select * from RESERVA where RESERVA.CLIENTE_ID = ?1 and RESERVA.DATA > ?2 and RESERVA.DATA < ?3", nativeQuery = true)
    List<Reserva> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino);

    @Transactional(readOnly = true)
    @Query(value = "select CLIENTE.NOME as nome, count(RESERVA.ID) as quantidade from RESERVA inner join CLIENTE on RESERVA.CLIENTE_ID = CLIENTE.ID where DATA > ?1 and DATA < ?2 group by RESERVA.CLIENTE_ID", nativeQuery = true)
    List<?> findQuantidadesReservasOfClientesByPeriodo(String inicio, String termino);
}
