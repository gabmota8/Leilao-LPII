package com.leilao.service;

import com.leilao.entity.Produto;
import com.leilao.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    @Transactional
    public Produto cadastrarProduto(Produto produto) {
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
