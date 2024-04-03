package com.example.demo.modulos.funcionario.model;

import  com.example.demo.modulos.funcionario.dto.FuncionarioRequest;
import com.example.demo.modulos.comum.model.Pessoa;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario extends Pessoa {
    private String login;

    private String senha;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, LocalDate dataNascimento, String login, String senha) {
        super(nome, cpf, dataNascimento);
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static Funcionario of(FuncionarioResponse request) {
        return new Funcionario(request.nome(), request.cpf(),
                request.dataNascimento(), request.login(), request.senha());
    }
}
