package com.leilao.service;

import com.leilao.entity.Leilao;
import com.leilao.repository.LeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LeilaoService {

    @Inject
    LeilaoRepository leilaoRepository;

    // Listar todos os leilões
    public List<Leilao> listarLeiloes() {
        return leilaoRepository.listAll();
    }

    // Buscar um leilão específico
    public Optional<Leilao> buscarPorId(Long id) {
        return leilaoRepository.findByIdOptional(id);
    }

    // Criar um novo leilão
    @Transactional
    public Leilao criarLeilao(Leilao leilao) {
        leilaoRepository.persist(leilao);
        return leilao;
    }

    // Atualizar um leilão existente
    @Transactional
    public Optional<Leilao> atualizarLeilao(Long id, Leilao leilaoAtualizado) {
        Optional<Leilao> leilaoOp = leilaoRepository.findByIdOptional(id);
        if (leilaoOp.isPresent()) {
            Leilao leilao = leilaoOp.get();
            leilao.setEndereco(leilaoAtualizado.getEndereco());
            leilao.setCidade(leilaoAtualizado.getCidade());
            leilao.setEstado(leilaoAtualizado.getEstado());
            leilao.setDataVisita(leilaoAtualizado.getDataVisita());
            leilao.setDataLeilao(leilaoAtualizado.getDataLeilao());
            leilao.setInstituicaoFinanceira(leilaoAtualizado.getInstituicaoFinanceira());
            leilaoRepository.persist(leilao);
            return Optional.of(leilao);
        }
        return Optional.empty();
    }

    // Remover um leilão pelo ID
    @Transactional
    public boolean removerLeilao(Long id) {
        return leilaoRepository.deleteById(id);
    }
}
