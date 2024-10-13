package com.leilao.repository;



import com.leilao.entity.*;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LanceRepository implements PanacheRepository<Lance> {
}
