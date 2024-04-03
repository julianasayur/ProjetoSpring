package com.example.demo.modulos.emprestimo.repository;

import com.example.demo.modulos.emprestimo.model.ItemDeEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDeEmprestimoRepository extends JpaRepository<ItemDeEmprestimo, Integer> {
}
