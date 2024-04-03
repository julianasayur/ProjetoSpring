package com.example.demo.modulos.livro.model;

import com.example.demo.modulos.livro.dto.LivroRequest;

import jakarta.persistence.*;

@Entity
@Table(name = "LIVRO")
public class Livro {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "GENERO", nullable = false)
    private String genero;

    @ManyToOne
    @JoinColumn(name = "FK_TIPO_DE_LIVRO",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_TIPO_DE_LIVRO"))
    private TipoDeLivro tipoDeLivro;

    public Livro() {
    }

    public Livro(String titulo, String genero, TipoDeLivro tipoDeLivro) {
        this.titulo = titulo;
        this.genero = genero;
        this.tipoDeLivro = tipoDeLivro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public TipoDeLivro getTipoDeLivro() {
        return tipoDeLivro;
    }

    public void setTipoDeLivro(TipoDeLivro tipoDeLivro) {
        this.tipoDeLivro = tipoDeLivro;
    }

    public static Livro of(LivroRequest request, TipoDeLivro tipoDeLivro) {
        return new Livro(request.titulo(), request.genero(), tipoDeLivro);
    }
}
