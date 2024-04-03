package com.example.demo.modulos.emprestimo.model;

import com.example.demo.modulos.emprestimo.dto.DevolucaoRequest;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DEVOLUCAO")
public class Devolucao {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DATA", nullable = false)
    private LocalDate data;

    @OneToOne
    @JoinColumn(name = "FK_ITEM_DE_EMPRESTIMO",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_ITEM_DE_EMPRESTIMO"))
    private ItemDeEmprestimo itemDeEmprestimo;

    public Devolucao(LocalDate data, ItemDeEmprestimo itemDeEmprestimo) {
        this.data = data;
        this.itemDeEmprestimo = itemDeEmprestimo;
    }

    public Devolucao() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ItemDeEmprestimo getItemDeEmprestimo() {
        return itemDeEmprestimo;
    }

    public void setItemDeEmprestimo(ItemDeEmprestimo itemDeEmprestimo) {
        this.itemDeEmprestimo = itemDeEmprestimo;
    }

    public static Devolucao of(DevolucaoRequest request, ItemDeEmprestimo itemDeEmprestimo) {
        return new Devolucao(request.data(), itemDeEmprestimo);
    }
}