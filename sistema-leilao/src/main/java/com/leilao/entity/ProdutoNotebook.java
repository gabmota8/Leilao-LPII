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
@DiscriminatorValue("NOTEBOOK")
public class ProdutoNotebook extends Produto {
    private String marca;
    private String modelo;
    private int memoriaRAM; // em GB
    private int armazenamento; // em GB

    
    public ProdutoNotebook(Long id, String nome, Double precoInicial, Leilao leilao, boolean vendido,
                           String marca, String modelo, int memoriaRAM, int armazenamento) {
        super(id, nome, precoInicial, leilao, vendido);
        this.marca = marca;
        this.modelo = modelo;
        this.memoriaRAM = memoriaRAM;
        this.armazenamento = armazenamento;
    }
}
