package com.example.demo.modulos.emprestimo.model;

import com.example.demo.modulos.cliente.model.Cliente;
import com.example.demo.modulos.emprestimo.dto.ReservaRequest;
import com.example.demo.modulos.livro.model.LivroFisico;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RESERVA")
public class Reserva {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DATA", nullable = false)
    private LocalDate data;

    @Column(name = "STATUS", nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "FK_CLIENTE",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_CLIENTE"))
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "FK_LIVRO_FISICO",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_LIVRO_FISICO"))
    private LivroFisico livroFisico;

    public Reserva() {

    }

    public Reserva(LocalDate data, Integer status, Cliente cliente, LivroFisico livroFisico) {
        this.data = data;
        this.status = status;
        this.cliente = cliente;
        this.livroFisico = livroFisico;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LivroFisico getLivroFisico() {
        return livroFisico;
    }

    public void setLivroFisico(LivroFisico livroFisico) {
        this.livroFisico = livroFisico;
    }

    public static Reserva of(ReservaRequest request, Cliente cliente, LivroFisico livroFisico) {
        return new Reserva(request.data(), request.status(), cliente, livroFisico);
    }
}
