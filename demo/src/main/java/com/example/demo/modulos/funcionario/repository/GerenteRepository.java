package com.example.demo.modulos.funcionario.repository;

import com.example.demo.modulos.funcionario.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Integer> {

    boolean existsByLoginIgnoreCase(String login);

    boolean existsByLoginIgnoreCaseAndIdNot(String login, Integer id);
}