

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
import java.util.Optional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class LanceService {

    @Inject
    LanceRepository lanceRepository;

    @Inject
    ProdutoRepository produtoRepository;

    private static final Logger logger = Logger.getLogger(LanceService.class);

    @Transactional
    public Optional<Lance> registrarLance(Lance lance) {
        logger.info("Registrando lance: " + lance);
        if (lance == null || lance.getProduto() == null || lance.getProduto().getId() == null) {
            logger.error("Lance ou Produto está nulo");
            throw new IllegalArgumentException("Lance ou Produto não pode ser nulo");
        }

        Optional<Produto> produtoOp = produtoRepository.findByIdOptional(lance.getProduto().getId());
        if (produtoOp.isPresent()) {
            Produto produto = produtoOp.get();
            List<Lance> lancesDoProduto = lanceRepository.list("produto", produto);

            // Verifica se o valor do lance é maior que o último lance
            if (lancesDoProduto.isEmpty() || lance.getValor() > lancesDoProduto.get(lancesDoProduto.size() - 1).getValor()) {
                lance.setDataHora(LocalDateTime.now());
                lanceRepository.persist(lance);
                logger.info("Lance registrado com sucesso: " + lance);
                return Optional.of(lance);
            } else {
                logger.warn("Valor do lance é menor ou igual ao último lance registrado.");
            }
        } else {
            logger.warn("Produto não encontrado: " + lance.getProduto().getId());
        }
        return Optional.empty();  // Lance inválido ou produto não encontrado
    }

    public List<Lance> obterHistoricoDeLances(Long produtoId) {
        logger.info("Obtendo histórico de lances para o produto: " + produtoId);
        return lanceRepository.list("produto.id", produtoId);
    }
}
