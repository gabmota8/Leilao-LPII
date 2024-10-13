package com.leilao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false) 
@Entity
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_produto")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo_produto")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProdutoNotebook.class, name = "NOTEBOOK"),
    @JsonSubTypes.Type(value = ProdutoMonitor.class, name = "MONITOR"),
    @JsonSubTypes.Type(value = ProdutoHub.class, name = "HUB"),
    @JsonSubTypes.Type(value = ProdutoSwitch.class, name = "SWITCH"),
    @JsonSubTypes.Type(value = ProdutoRoteador.class, name = "ROTEADOR")
})
public abstract class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double precoInicial;

    @ManyToOne
    @JoinColumn(name = "leilao_id")
    private Leilao leilao;

    private boolean vendido;
}
