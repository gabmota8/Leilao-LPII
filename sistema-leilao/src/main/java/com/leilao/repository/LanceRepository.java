package com.leilao.repository;

import com.leilao.entity.Lance;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LanceRepository implements PanacheRepository<Lance> {
}
