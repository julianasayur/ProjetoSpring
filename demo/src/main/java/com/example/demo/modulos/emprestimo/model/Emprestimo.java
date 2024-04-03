package com.example.demo.modulos.emprestimo.model;

import com.example.demo.modulos.cliente.model.Cliente;
import com.example.demo.modulos.emprestimo.dto.EmprestimoRequest;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "EMPRESTIMO")
public class Emprestimo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DATA", nullable = false)
    private LocalDate data;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(
            name = "FK_CLIENTE",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_CLIENTE"),
            nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDeEmprestimo> itens;

    public Emprestimo(LocalDate data, Double valor, Cliente cliente) {
        this.data = data;
        this.valor = valor;
        this.cliente = cliente;
    }

    public Emprestimo() {

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemDeEmprestimo> getItens() {
        return itens;
    }

    public void setItens(List<ItemDeEmprestimo> itens) {
        this.itens = itens;
    }

    public static Emprestimo of(EmprestimoRequest request, Cliente cliente) {
        return new Emprestimo(request.data(), request.valor(), cliente);
    }
}

