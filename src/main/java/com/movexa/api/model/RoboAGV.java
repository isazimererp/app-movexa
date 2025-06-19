package com.movexa.api.model;

import jakarta.persistence.*;

@Entity
public class RoboAGV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificador;
    private Double consumoBateria;
    private Double nivelBateria;
    private Integer tempoEntreManutencoes;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }

    public Double getConsumoBateria() { return consumoBateria; }
    public void setConsumoBateria(Double consumoBateria) { this.consumoBateria = consumoBateria; }

    public Double getNivelBateria() { return nivelBateria; }
    public void setNivelBateria(Double nivelBateria) { this.nivelBateria = nivelBateria; }

    public Integer getTempoEntreManutencoes() { return tempoEntreManutencoes; }
    public void setTempoEntreManutencoes(Integer tempoEntreManutencoes) { this.tempoEntreManutencoes = tempoEntreManutencoes; }
}