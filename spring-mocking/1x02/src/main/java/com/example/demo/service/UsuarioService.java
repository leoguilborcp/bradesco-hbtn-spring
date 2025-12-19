package com.example.demo.service;


import com.example.demo.model.Produto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

     public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }   
}