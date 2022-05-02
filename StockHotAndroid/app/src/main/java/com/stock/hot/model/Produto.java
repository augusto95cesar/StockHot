package com.stock.hot.model;

import java.io.Serializable;

public class Produto implements Serializable {

    private String nome;
    private String codeBarra;
    private Double valor;
    private Integer quantidade;

    //Consturtor
    public  Produto (){}

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
