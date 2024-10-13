package com.leilao.repository;

import com.leilao.entity.Leilao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class LeilaoRepository implements PanacheRepository<Leilao> {
}
