package com.example.demo.modulos.livro.repository;

import com.example.demo.modulos.livro.model.LivroFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LivroFisicoRepository extends JpaRepository<LivroFisico, Integer> {

    @Transactional(readOnly = true)
    List<LivroFisico> findByLivro_Id(Integer livroId);

    @Transactional(readOnly = true)
    List<LivroFisico> findByDanificadaAndDisponivel(Boolean danificada, Boolean disponivel);

    @Transactional(readOnly = true)
    boolean existsLivroFisicoByIdAndDisponivelFalse(Integer id);

}
