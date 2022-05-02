package com.stock.hot.model;

import java.io.Serializable;

public class Produto implements Serializable {

    private String nome = "";
    private String codeBarra = "";
    private Double valor = 0.0;
    private Integer quantidade = 0;

    //Consturtor
    public  Produto (){}

    public Produto(String nome, String codeBarra, Double valor, Integer quantidade) {
        this.nome = nome;
        this.codeBarra = codeBarra;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodeBarra() {
        return codeBarra;
    }

    public void setCodeBarra(String codeBarra) {
        this.codeBarra = codeBarra;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
