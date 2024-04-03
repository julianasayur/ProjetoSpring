package com.example.demo.modulos.funcionario.model;

import com.example.demo.modulos.funcionario.dto.GerenteRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "GERENTE")
public class Gerente extends Funcionario {

    @Column(name = "NUM_FUNC_GERENCIADOS")
    private int numFuncGerenciados;

    public int getNumFuncGerenciados() {
        return numFuncGerenciados;
    }

    public Gerente() {
    }

    public Gerente(String nome, String cpf, LocalDate dataNascimento, String login,
                   String senha, int numFuncGerenciados) {
        super(nome, cpf, dataNascimento, login, senha);
        this.numFuncGerenciados = numFuncGerenciados;
    }

    public void setNumFuncGerenciados(int numFuncGerenciados) {
        this.numFuncGerenciados = numFuncGerenciados;
    }

    public static Gerente of(GerenteRequest request) {
        return new Gerente(request.nome(), request.cpf(), request.dataNascimento(), request.login(),
                request.senha(), request.numFuncGerenciados());
    }
}
