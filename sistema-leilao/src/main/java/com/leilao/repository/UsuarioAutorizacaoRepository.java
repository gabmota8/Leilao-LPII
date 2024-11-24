package com.leilao.repository;

import com.leilao.entity.UsuarioAutorizacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioAutorizacaoRepository implements PanacheRepository<UsuarioAutorizacao> {
}
