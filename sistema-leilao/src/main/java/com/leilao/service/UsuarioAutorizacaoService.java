package com.leilao.service;

import java.util.List;

import com.leilao.entity.UsuarioAutorizacao;
import com.leilao.repository.UsuarioAutorizacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioAutorizacaoService {
    
    @Inject
    UsuarioAutorizacaoRepository usuarioAutorizacaoRepository;

    @Transactional
    public UsuarioAutorizacao cadastrarUsuarioAutorizacao(UsuarioAutorizacao usuarioAutorizacao) {
        usuarioAutorizacaoRepository.persist(usuarioAutorizacao);
        return usuarioAutorizacao;
    }

    public List<UsuarioAutorizacao> listarTodos() {
        return usuarioAutorizacaoRepository.findAll().list();
    }

    public UsuarioAutorizacao buscarPorId(Long id) {
        return usuarioAutorizacaoRepository.findById(id);
    }

    @Transactional
    public void excluirUsuarioAutorizacao(Long id) {
        UsuarioAutorizacao usuarioAutorizacao = usuarioAutorizacaoRepository.findById(id);
        if (usuarioAutorizacao != null) {
            usuarioAutorizacaoRepository.delete(usuarioAutorizacao);
        } else {
            throw new IllegalArgumentException("Usuário autorização não encontrado");
        }
    }
}
