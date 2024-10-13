package com.leilao.service;


import com.leilao.entity.Produto;
import com.leilao.repository.ProdutoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.listAll();
    }

    // Buscar um produto específico por ID
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findByIdOptional(id);
    }

    // Criar um novo produto (pode ser dispositivo ou veículo)
    @Transactional
    public Produto criarProduto(Produto produto) {
        produtoRepository.persist(produto);
        return produto;
    }

    // Atualizar um produto existente
    @Transactional
    public Optional<Produto> atualizarProduto(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoOp = produtoRepository.findByIdOptional(id);
        if (produtoOp.isPresent()) {
            Produto produto = produtoOp.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setPrecoInicial(produtoAtualizado.getPrecoInicial());
            produto.setLeilao(produtoAtualizado.getLeilao());
            produto.setVendido(produtoAtualizado.isVendido());
            produtoRepository.persist(produto);
            return Optional.of(produto);
        }
        return Optional.empty();
    }

    // Remover um produto pelo ID
    @Transactional
    public boolean removerProduto(Long id) {
        return produtoRepository.deleteById(id);
    }
}
