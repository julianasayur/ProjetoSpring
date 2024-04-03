package com.example.demo.modulos.livro.model;

import com.example.demo.modulos.livro.dto.LivroFisicoRequest;

import jakarta.persistence.*;

@Entity
@Table(name = "LIVRO_FISICO")
public class LivroFisico {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DANIFICADA", nullable = false)
    private boolean danificada;

    @Column(name = "DISPONIVEL", nullable = false)
    private boolean disponivel;

    @ManyToOne
    @JoinColumn(name = "FK_LIVRO",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_LIVRO"))
    private Livro livro;

    public LivroFisico(boolean danificada, boolean disponivel, Livro livro) {
        this.danificada = danificada;
        this.disponivel = disponivel;
        this.livro = livro;
    }

    public LivroFisico() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getDanificada() {
        return danificada;
    }

    public void setDanificada(boolean danificada) {
        this.danificada = danificada;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public static LivroFisico of(LivroFisicoRequest request, Livro livro) {
        return new LivroFisico(request.danificada(), request.disponivel(), livro);
    }
}
