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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodos'");
    }

    public UsuarioAutorizacao buscarPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    public void excluirUsuarioAutorizacao(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluirUsuarioAutorizacao'");
    }
}
