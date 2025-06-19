package com.movexa.api.controller;

import com.movexa.api.dto.RoboAGVDTO;
import com.movexa.api.model.RoboAGV;
import com.movexa.api.repository.RoboAGVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/robos-agv")
public class RoboAGVController {

    @Autowired
    private RoboAGVRepository roboAGVRepository;

    @GetMapping
    public List<RoboAGVDTO> listar() {
        return roboAGVRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<RoboAGVDTO> criar(@Valid @RequestBody RoboAGVDTO dto) {
        RoboAGV robo = toEntity(dto);
        RoboAGV salvo = roboAGVRepository.save(robo);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody RoboAGVDTO dto) {
        Optional<RoboAGV> opt = roboAGVRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        RoboAGV robo = opt.get();
        robo.setIdentificador(dto.getIdentificador());
        robo.setConsumoBateria(dto.getConsumoBateria());
        robo.setNivelBateria(dto.getNivelBateria());
        robo.setTempoEntreManutencoes(dto.getTempoEntreManutencoes());
        roboAGVRepository.save(robo);
        return ResponseEntity.ok(toDTO(robo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (!roboAGVRepository.existsById(id)) return ResponseEntity.notFound().build();
        roboAGVRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos auxiliares para conversão
    private RoboAGVDTO toDTO(RoboAGV r) {
        RoboAGVDTO dto = new RoboAGVDTO();
        dto.setId(r.getId());
        dto.setIdentificador(r.getIdentificador());
        dto.setConsumoBateria(r.getConsumoBateria());
        dto.setNivelBateria(r.getNivelBateria());
        dto.setTempoEntreManutencoes(r.getTempoEntreManutencoes());
        return dto;
    }

    private RoboAGV toEntity(RoboAGVDTO dto) {
        RoboAGV r = new RoboAGV();
        r.setIdentificador(dto.getIdentificador());
        r.setConsumoBateria(dto.getConsumoBateria());
        r.setNivelBateria(dto.getNivelBateria());
        r.setTempoEntreManutencoes(dto.getTempoEntreManutencoes());
        return r;
    }
}