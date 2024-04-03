package com.example.demo.modulos.livro.repository;

import com.example.demo.modulos.livro.model.TipoDeLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TipoDeLivroRepository extends JpaRepository<TipoDeLivro, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCaseAndIdNot(String nome, Integer id);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM TIPO_DE_LIVRO WHERE TIPO_DE_LIVRO.PRECO > ?1", nativeQuery = true)
    List<TipoDeLivro> findMaioresPrecos(Double preco);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM TIPO_DE_LIVRO WHERE TIPO_DE_LIVRO.PRECO < ?1", nativeQuery = true)
    List<TipoDeLivro> findMenoresPrecos(Double preco);
}