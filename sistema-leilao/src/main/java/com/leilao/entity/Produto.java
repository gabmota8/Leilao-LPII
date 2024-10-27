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
@Table(name = "pro_produto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pro_tipo")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "pro_tipo")
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
    @Column(name = "pro_id")
    private Long id;

    @Column(name = "pro_nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "pro_preco_inicial", nullable = false)
    private Double precoInicial;

    @ManyToOne
    @JoinColumn(name = "lei_id", nullable = false)
    private Leilao leilao;

    @Column(name = "pro_vendido", nullable = false)
    private boolean vendido;
}
