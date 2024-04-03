package com.example.demo.modulos.livro.repository;

import com.example.demo.modulos.livro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    boolean existsByTituloIgnoreCase(String titulo);

    boolean existsByTituloIgnoreCaseAndIdNot(String titulo, Integer id);

    @Transactional(readOnly = true)
    List<Livro> findByTipoDeLivroId(Integer tipoDeLivroId);
}
