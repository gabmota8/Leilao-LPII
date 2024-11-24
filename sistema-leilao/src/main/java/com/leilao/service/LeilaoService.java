package com.leilao.service;

import com.leilao.entity.Leilao;
import com.leilao.repository.LeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class LeilaoService {

    @Inject
    LeilaoRepository leilaoRepository;

    @Transactional
    public Leilao cadastrarLeilao(Leilao leilao) {
        leilaoRepository.persist(leilao);
        return leilao;
    }

    public List<Leilao> listarTodos() {
        return leilaoRepository.findAll().list();
    }

    public Leilao buscarPorId(Long id) {
        return leilaoRepository.findById(id);
    }

    @Transactional
    public void excluirLeilao(Long id) {
        Leilao leilao = leilaoRepository.findById(id);
        if (leilao != null) {
            leilaoRepository.delete(leilao);
        }
    }
}
