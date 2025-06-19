package com.movexa.api.controller;

import com.movexa.api.dto.UsuarioDTO;
import com.movexa.api.model.Usuario;
import com.movexa.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = toEntity(dto);
        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Usuario usuario = opt.get();
        usuario.setEmail(dto.getEmail());
        usuario.setSenhaHash(dto.getSenha());
        usuario.setPapel(dto.getPapel());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(toDTO(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) return ResponseEntity.notFound().build();
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos auxiliares para conversão
    private UsuarioDTO toDTO(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmail(u.getEmail());
        dto.setSenha(u.getSenhaHash());
        dto.setPapel(u.getPapel());
        return dto;
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario u = new Usuario();
        u.setEmail(dto.getEmail());
        u.setSenhaHash(dto.getSenha());
        u.setPapel(dto.getPapel());
        return u;
    }
}