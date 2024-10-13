package com.leilao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leiloes")
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;
    private String cidade;
    private String estado;
    private LocalDateTime dataVisita;
    private LocalDateTime dataLeilao;

    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "instituicao_financeira_id")
    private InstituicaoFinanceira instituicaoFinanceira;

    private String status; // Em Aberto, Em Andamento, Finalizado
}