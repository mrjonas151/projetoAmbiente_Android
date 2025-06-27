package com.example.projeto4_mobile.models;

public class RelatorioPersonalizado {
    private String relatorio;
    private int totalAtividades;
    private String periodoAnalise;

    public RelatorioPersonalizado() { }

    public String getRelatorio() { return relatorio; }
    public void setRelatorio(String relatorio) { this.relatorio = relatorio; }

    public int getTotalAtividades() { return totalAtividades; }
    public void setTotalAtividades(int totalAtividades) { this.totalAtividades = totalAtividades; }

    public String getPeriodoAnalise() { return periodoAnalise; }
    public void setPeriodoAnalise(String periodoAnalise) { this.periodoAnalise = periodoAnalise; }
}