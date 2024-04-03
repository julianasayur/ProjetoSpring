package com.example.demo.modulos.emprestimo.repository;

import com.example.demo.modulos.emprestimo.model.Devolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DevolucaoRepository extends JpaRepository<Devolucao, Integer> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT distinct DEVOLUCAO.EMPRESTIMO_ID, DEVOLUCAO.LIVRO_FISICO_ID, DEVOLUCAO.DATA FROM DEVOLUCAO join EMPRESTIMO on DEVOLUCAO.EMPRESTIMO_ID = EMPRESTIMO.ID join CLIENTE on EMPRESTIMO.CLIENTE_ID = ?1 where DEVOLUCAO.DATA > ?2 and DEVOLUCAO.DATA < ?3", nativeQuery = true)
    List<Devolucao> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino);

    @Transactional(readOnly = true)
    @Query(value = "select CLIENTE.NOME as nome, count(DEVOLUCAO.DATA) as quantidade from DEVOLUCAO inner join EMPRESTIMO on DEVOLUCAO.EMPRESTIMO_ID = EMPRESTIMO.ID inner join CLIENTE on EMPRESTIMO.CLIENTE_ID = CLIENTE.ID where DEVOLUCAO.DATA > ?1 and DEVOLUCAO.DATA < ?2 group by CLIENTE.NOME", nativeQuery = true)
    List<?> findQuantidadeDevolucaoClienteByPeriodo(String inicio, String termino);
}
