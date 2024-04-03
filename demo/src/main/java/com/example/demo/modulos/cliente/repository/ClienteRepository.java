package com.example.demo.modulos.cliente.repository;

import com.example.demo.modulos.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    boolean existsByCpfIgnoreCase(String cpf);

    boolean existsByCpfIgnoreCaseAndIdNot(String cpf, Integer id);
}
