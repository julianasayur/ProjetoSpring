package com.example.demo.modulos.emprestimo.model;

import com.example.demo.modulos.emprestimo.dto.MultaRequest;

import jakarta.persistence.*;

@Entity
@Table(name = "MULTA")
public class Multa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "PAGO")
    private boolean pago;

    @OneToOne
    @JoinColumn(name = "FK_ITEM_DE_EMPRESTIMO",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_ITEM_DE_EMPRESTIMO"))
    private ItemDeEmprestimo itemDeEmprestimo = new ItemDeEmprestimo();

    public Multa(Double valor, boolean pago, ItemDeEmprestimo itemDeEmprestimo) {
        this.valor = valor;
        this.pago = pago;
        this.itemDeEmprestimo = itemDeEmprestimo;
    }

    public Multa() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public ItemDeEmprestimo getItemDeEmprestimo() {
        return itemDeEmprestimo;
    }

    public void setItemDeEmprestimo(ItemDeEmprestimo itemDeEmprestimo) {
        this.itemDeEmprestimo = itemDeEmprestimo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public static Multa of(MultaRequest request, ItemDeEmprestimo itemDeEmprestimo) {
        return new Multa(request.valor(), request.pago(), itemDeEmprestimo);
    }
}