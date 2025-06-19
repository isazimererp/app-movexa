package com.movexa.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoboAGVDTO {
    private Long id;

    @NotBlank(message = "Identificador é obrigatório")
    private String identificador;

    @NotNull(message = "Consumo de bateria é obrigatório")
    private Double consumoBateria;

    @NotNull(message = "Nível de bateria é obrigatório")
    private Double nivelBateria;

    @NotNull(message = "Tempo entre manutenções é obrigatório")
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