package com.example.demo.modulos.emprestimo.model;

import com.example.demo.modulos.emprestimo.dto.ItemDeEmprestimoRequest;
import com.example.demo.modulos.livro.model.LivroFisico;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ITEM_DE_EMPRESTIMO")
public class ItemDeEmprestimo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "DATA", nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "FK_LIVRO_FISICO",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_LIVRO_FISICO"))
    private LivroFisico livroFisico;

    @ManyToOne
    @JoinColumn(name = "FK_EMPRESTIMO",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_EMPRESTIMO"))
    private Emprestimo emprestimo;

    public ItemDeEmprestimo(Double valor, LocalDate data, LivroFisico livroFisico, Emprestimo emprestimo) {
        this.valor = valor;
        this.data = data;
        this.livroFisico = livroFisico;
        this.emprestimo = emprestimo;
    }

    public ItemDeEmprestimo(Double valor, LocalDate data, LivroFisico livroFisico) {
        this.valor = valor;
        this.data = data;
        this.livroFisico = livroFisico;
    }

    public ItemDeEmprestimo() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LivroFisico getLivroFisico() {
        return livroFisico;
    }

    public void setLivroFisico(LivroFisico livroFisico) {
        this.livroFisico = livroFisico;
    }

    public LocalDate getData() {
        return data;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public static ItemDeEmprestimo of(ItemDeEmprestimoRequest request, LivroFisico livroFisico, Emprestimo emprestimo) {
        return new ItemDeEmprestimo(request.valor(), request.data(), livroFisico, emprestimo);
    }

    public static ItemDeEmprestimo of(ItemDeEmprestimoRequest request, LivroFisico livroFisico) {
        return new ItemDeEmprestimo(request.valor(), request.data(), livroFisico);
    }
}
