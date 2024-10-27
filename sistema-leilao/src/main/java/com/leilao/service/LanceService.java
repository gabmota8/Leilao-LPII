package com.leilao.service;

import com.leilao.entity.Lance;
import com.leilao.repository.LanceRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class LanceService {

    @Inject
    LanceRepository lanceRepository;

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
