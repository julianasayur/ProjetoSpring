package com.example.demo.modulos.emprestimo.repository;

import com.example.demo.modulos.emprestimo.model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultaRepository extends JpaRepository<Multa, Integer> {
}
