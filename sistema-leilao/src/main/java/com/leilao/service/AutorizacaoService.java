package com.leilao.service;

import java.util.List;

import com.leilao.entity.Autorizacao;
import com.leilao.repository.AutorizacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AutorizacaoService {
    
    @Inject
    AutorizacaoRepository autorizacaoRepository;

    @Transactional
    public Autorizacao cadastrarAutorizacao(Autorizacao autorizacao) {
        autorizacaoRepository.persist(autorizacao);
        return autorizacao;
    }

    public List<Autorizacao> listarTodos() {
        
        throw new UnsupportedOperationException("Unimplemented method 'listarTodos'");
    }

    public Autorizacao buscarPorId(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    public void excluirAutorizacao(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'excluirAutorizacao'");
    }
}
