package com.example.projeto4_mobile.models;

public class AtividadeSustentavel {
    private int id;
    private String tipo;
    private String descricao;
    private String data;
    private double impactoAmbiental;
    private String unidadeImpacto;
    private String dataCriacao;
    private String dataAtualizacao;

    public AtividadeSustentavel() { }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public double getImpactoAmbiental() { return impactoAmbiental; }
    public void setImpactoAmbiental(double impactoAmbiental) { this.impactoAmbiental = impactoAmbiental; }

    public String getUnidadeImpacto() { return unidadeImpacto; }
    public void setUnidadeImpacto(String unidadeImpacto) { this.unidadeImpacto = unidadeImpacto; }

    public String getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(String dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(String dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}
