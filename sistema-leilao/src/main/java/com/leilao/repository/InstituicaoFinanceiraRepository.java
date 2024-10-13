package com.leilao.repository;

import com.leilao.entity.InstituicaoFinanceira;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


    @ApplicationScoped
public class InstituicaoFinanceiraRepository implements PanacheRepository<InstituicaoFinanceira> {
}



