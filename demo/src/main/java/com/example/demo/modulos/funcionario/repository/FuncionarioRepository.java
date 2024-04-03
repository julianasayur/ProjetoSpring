package com.example.demo.modulos.funcionario.repository;

import com.example.demo.modulos.funcionario.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    boolean existsByLoginIgnoreCase(String login);

    boolean existsByLoginIgnoreCaseAndIdNot(String login, Integer id);

    @Transactional(readOnly = true)
    Funcionario findByLoginAndSenha(String login, String senha);
}