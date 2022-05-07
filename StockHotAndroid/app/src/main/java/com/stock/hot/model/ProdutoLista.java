package com.stock.hot.model;

import java.io.Serializable;

public class ProdutoLista extends  Produto implements Serializable {
    private String identificado;

    public ProdutoLista() { }

    public ProdutoLista(String nome, String codeBarra, Double valor, Integer quantidade, String identificado) {
        super(nome, codeBarra, valor, quantidade);
        this.identificado = identificado;
    }

    public String getIdentificado() {
        return identificado;
    }

    public void setIdentificado(String identificado) {
        this.identificado = identificado;
    }
}
