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
@DiscriminatorValue("MONITOR")
public class ProdutoMonitor extends Produto {
    private String marca;
    private int tamanho; // em polegadas
    private String resolucao; // exemplo: 1920x1080

   
    public ProdutoMonitor(Long id, String nome, Double precoInicial, Leilao leilao, boolean vendido,
                          String marca, int tamanho, String resolucao) {
        super(id, nome, precoInicial, leilao, vendido);
        this.marca = marca;
        this.tamanho = tamanho;
        this.resolucao = resolucao;
    }
}
