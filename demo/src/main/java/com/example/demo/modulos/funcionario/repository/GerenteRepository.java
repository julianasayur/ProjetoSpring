package com.example.demo.modulos.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modulos.funcionario.model.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
}
