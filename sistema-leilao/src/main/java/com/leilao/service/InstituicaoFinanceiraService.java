package com.leilao.service;



import com.leilao.entity.InstituicaoFinanceira;
import com.leilao.repository.InstituicaoFinanceiraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class InstituicaoFinanceiraService {

    @Inject
    InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

    @Transactional
    public InstituicaoFinanceira cadastrarInstituicao(InstituicaoFinanceira instituicao) {
        instituicaoFinanceiraRepository.persist(instituicao);
        return instituicao;
    }

    public List<InstituicaoFinanceira> listarTodas() {
        return instituicaoFinanceiraRepository.findAll().list();
    }

    public InstituicaoFinanceira buscarPorId(Long id) {
        return instituicaoFinanceiraRepository.findById(id);
    }

    @Transactional
    public void excluirInstituicao(Long id) {
        instituicaoFinanceiraRepository.deleteById(id);
    }
}
