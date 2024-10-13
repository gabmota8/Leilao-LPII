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
@DiscriminatorValue("SWITCH")
public class ProdutoSwitch extends Produto {
    private int portasEthernet;


    public ProdutoSwitch(Long id, String nome, Double precoInicial, Leilao leilao, boolean vendido, int portasEthernet) {
        super(id, nome, precoInicial, leilao, vendido);
        this.portasEthernet = portasEthernet;
    }
}
