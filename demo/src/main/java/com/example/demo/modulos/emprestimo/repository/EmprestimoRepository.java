package com.example.demo.modulos.emprestimo.repository;

import com.example.demo.modulos.emprestimo.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

    @Transactional(readOnly = true)
    List<Emprestimo> findByClienteId(Integer clienteId);

    @Transactional(readOnly = true)
    @Query(value = "select * from EMPRESTIMO where EMPRESTIMO.CLIENTE_ID = ?1 and EMPRESTIMO.DATA > ?2 and EMPRESTIMO.DATA < ?3", nativeQuery = true)
    List<Emprestimo> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino);

    @Transactional(readOnly = true)
    @Query(value = "select CLIENTE.NOME as nome, sum(VALOR) as total, count(VALOR) as quantidade from EMPRESTIMO inner join CLIENTE on EMPRESTIMO.CLIENTE_ID = CLIENTE.ID where DATA >= ?1 and DATA <= ?2 group by EMPRESTIMO.CLIENTE_ID", nativeQuery = true)
    List<?> findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(String inicio, String termino);

    @Transactional(readOnly = true)
    @Query(value = "select LIVRO.TITULO as LIVRO, count(ITEM_DE_EMPRESTIMO.ENTREGA) as QUANTIDADE from ITEM_DE_EMPRESTIMO inner join LIVRO_FISICO on ITEM_DE_EMPRESTIMO.LIVRO_FISICO_ID = LIVRO_FISICO.ID inner join LIVRO on LIVRO_FISICO.LIVRO_ID = LIVRO.ID where ITEM_DE_EMPRESTIMO.ENTREGA > ?1 and ITEM_DE_EMPRESTIMO.ENTREGA < ?2 group by LIVRO.TITULO", nativeQuery = true)
    List<?> findQuantidadesEmprestimosOfLivroByPeriodo(String inicio, String termino);

    @Transactional(readOnly = true)
    @Query(value = "select count(EMPRESTIMO.ID), extract(year from data) as ano, extract(month from data) as mes from EMPRESTIMO group by ano, mes order by ano, mes", nativeQuery = true)
    List<?> findTotaisAnoMes();
}

