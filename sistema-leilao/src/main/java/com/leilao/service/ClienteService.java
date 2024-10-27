package com.leilao.service;

import com.leilao.entity.Cliente;
import com.leilao.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Transactional
    public void cadastrarCliente(Cliente cliente) {
        clienteRepository.persist(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll().list();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
