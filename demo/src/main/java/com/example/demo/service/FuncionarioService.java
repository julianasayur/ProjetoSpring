package com.example.demo.service;

import com.example.demo.model.Funcionario;
import com.example.demo.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario criar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Long id, Funcionario funcionario) {
        //verificar se o id é valido
        if(funcionarioRepository.existsById(id)) {
            //atualizar o objeto na base
            funcionario.setId(id);
            return funcionarioRepository.save(funcionario);
        }
        return null;
        // não realiza nenhuma alteração
    }

    public boolean deletar(Long id) {
        if(funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public int qtdFuncionarios () {
        return funcionarioRepository.findAll().size();
    }
}
