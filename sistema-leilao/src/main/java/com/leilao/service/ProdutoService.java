package com.leilao.service;

import com.leilao.entity.Produto;
import com.leilao.entity.Leilao;
import com.leilao.repository.ProdutoRepository;
import com.leilao.repository.LeilaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    LeilaoRepository leilaoRepository;

    @Transactional
    public Produto cadastrarProduto(Produto produto) {
        if (produto.getLeilao() != null) {
            Leilao leilao = leilaoRepository.findById(produto.getLeilao().getId());
            if (leilao == null) {
                throw new IllegalArgumentException("Leilão não encontrado");
            }
            produto.setLeilao(leilao);
        }

        produtoRepository.persist(produto);
        return produto;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll().list();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
