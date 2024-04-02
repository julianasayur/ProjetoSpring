package com.example.demo.modulos.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modulos.funcionario.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
}
