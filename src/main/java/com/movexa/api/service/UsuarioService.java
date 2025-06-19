package com.movexa.api.service;

import com.movexa.api.dto.UsuarioDTO;
import com.movexa.api.model.Usuario;
import com.movexa.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Importa a classe BCryptPasswordEncoder para criptografar senhas

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(UsuarioDTO dto) {
        // Verifica se o e-mail já existe
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        // Converte DTO para entidade
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setPapel(dto.getPapel());

        // Criptografa a senha
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.getSenha());
        usuario.setSenhaHash(senhaCriptografada);

        // Salva no banco
        return usuarioRepository.save(usuario);
    }

    // Busca um usuário por ID
    public Usuario obterUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        return usuarioOpt.orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    // Método para autenticação (login)
    public Usuario autenticar(String email, String senhaHash) {
        return usuarioRepository.findByEmailAndSenhaHash(email, senhaHash)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }
}