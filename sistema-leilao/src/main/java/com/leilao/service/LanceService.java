package com.leilao.service;

import com.leilao.entity.Lance;
import com.leilao.entity.Produto;
import com.leilao.repository.LanceRepository;
import com.leilao.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class LanceService {

    @Inject
    LanceRepository lanceRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Transactional
    public Lance cadastrarLance(Lance lance) {
        lanceRepository.persist(lance);
        return lance;
    }

    public List<Lance> listarTodos() {
        return lanceRepository.findAll().list();
    }

    public Lance buscarPorId(Long id) {
        return lanceRepository.findById(id);
    }

    @Transactional
    public void excluirLance(Long id) {
        Lance lance = lanceRepository.findById(id);
        if (lance != null) {
            lanceRepository.delete(lance);
        }
    }
}
