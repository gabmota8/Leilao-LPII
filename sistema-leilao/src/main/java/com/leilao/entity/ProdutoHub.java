package com.leilao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("HUB")
@EqualsAndHashCode(callSuper = true)
public class ProdutoHub extends Produto {
    private int portasUSB;

 
    public ProdutoHub(Long id, String nome, Double precoInicial, Leilao leilao, boolean vendido, int portasUSB) {
        super(id, nome, precoInicial, leilao, vendido);
        this.portasUSB = portasUSB;
    }
}
