package com.example.demo.modulos.cliente.model;

import com.example.demo.modulos.cliente.dto.ClienteRequest;
import com.example.demo.modulos.comum.model.Pessoa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "CLIENTE")
public class Cliente extends Pessoa {

    @Column(name = "DEBITO", nullable = false)
    private Double debito;
    @Column(name = "TELEFONE", nullable = false)
    private String telefone;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, Double debito, String telefone) {
        super(nome, cpf, dataNascimento);
        this.debito = debito;
        this.telefone = telefone;
    }

    public Cliente() {

    }

    public Double getDebito() {
        return debito;
    }

    public void setDebito(Double debito) {
        this.debito = debito;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public static Cliente of(ClienteRequest request) {
        return new Cliente(request.nome(), request.cpf(), request.dataNascimento(),
                request.debito(), request.telefone());
    }
}
