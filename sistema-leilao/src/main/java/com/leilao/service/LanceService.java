package com.leilao.service;


import com.leilao.entity.Lance;
import com.leilao.entity.Produto;
import com.leilao.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LanceService {

    @Inject
    LanceRepository lanceRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Transactional
    public Optional<Lance> registrarLance(Lance lance) {
        Optional<Produto> produtoOp = produtoRepository.findByIdOptional(lance.getProduto().getId());
        if (produtoOp.isPresent()) {
            Produto produto = produtoOp.get();
            List<Lance> lancesDoProduto = lanceRepository.list("produto", produto);
            
            // Verifica se o valor do lance é maior que o último lance
            if (lancesDoProduto.isEmpty() || lance.getValor() > lancesDoProduto.get(lancesDoProduto.size() - 1).getValor()) {
                lance.setDataHora(LocalDateTime.now());
                lanceRepository.persist(lance);
                return Optional.of(lance);
            }
        }
        return Optional.empty();  // Lance inválido ou produto não encontrado
    }

    public List<Lance> obterHistoricoDeLances(Long produtoId) {
        return lanceRepository.list("produto.id", produtoId);
    }
}
