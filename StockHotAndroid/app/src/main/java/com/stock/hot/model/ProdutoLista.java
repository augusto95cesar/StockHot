package com.stock.hot.model;

public class ProdutoLista extends  Produto{
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
