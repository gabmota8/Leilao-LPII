package com.leilao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("ROTEADOR")
public class ProdutoRoteador extends Produto {
    private String frequencia; // exemplo: 2.4 GHz, 5 GHz

  
    public ProdutoRoteador(Long id, String nome, Double precoInicial, Leilao leilao, boolean vendido, String frequencia) {
        super(id, nome, precoInicial, leilao, vendido);
        this.frequencia = frequencia;
    }
}
