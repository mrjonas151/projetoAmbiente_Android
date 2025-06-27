package com.example.projeto4_mobile.models;

import java.util.Map;

public class Estatisticas {
    private int totalAtividades;
    private double impactoTotalPositivo;
    private Map<String, Integer> atividadesPorTipo;
    private double mediaImpactoDiario;

    public Estatisticas() { }

    public int getTotalAtividades() { return totalAtividades; }
    public void setTotalAtividades(int totalAtividades) { this.totalAtividades = totalAtividades; }

    public double getImpactoTotalPositivo() { return impactoTotalPositivo; }
    public void setImpactoTotalPositivo(double impactoTotalPositivo) { this.impactoTotalPositivo = impactoTotalPositivo; }

    public Map<String, Integer> getAtividadesPorTipo() { return atividadesPorTipo; }
    public void setAtividadesPorTipo(Map<String, Integer> atividadesPorTipo) { this.atividadesPorTipo = atividadesPorTipo; }

    public double getMediaImpactoDiario() { return mediaImpactoDiario; }
    public void setMediaImpactoDiario(double mediaImpactoDiario) { this.mediaImpactoDiario = mediaImpactoDiario; }
}