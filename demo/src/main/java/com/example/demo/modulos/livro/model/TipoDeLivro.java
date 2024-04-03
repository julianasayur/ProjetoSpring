package com.example.demo.modulos.livro.model;

import com.example.demo.modulos.livro.dto.TipoDeLivroRequest;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_DE_LIVRO")
public class TipoDeLivro {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "PRAZO", nullable = false)
    private Integer prazo;

    @Column(name = "PRECO", nullable = false)
    private Double preco;

    public TipoDeLivro() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public TipoDeLivro(String nome, Integer prazo, Double preco) {
        this.nome = nome;
        this.prazo = prazo;
        this.preco = preco;
    }

    public static TipoDeLivro of(TipoDeLivroRequest request) {
        return new TipoDeLivro(request.nome(), request.prazo(), request.preco());
    }
}
