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
@Table(name = "lei_leilao")
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lei_id")
    private Long id;

    @Column(name = "lei_endereco", nullable = false, length = 200)
    private String endereco;

    @Column(name = "lei_cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "lei_estado", nullable = false, length = 2)
    private String estado;

    @Column(name = "lei_data_visita", nullable = false)
    private LocalDateTime dataVisita;

    @Column(name = "lei_data_leilao", nullable = false)
    private LocalDateTime dataLeilao;

    @Column(name = "lei_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusLeilao status;

    @ManyToOne
    @JoinColumn(name = "ins_id", nullable = false)
    private InstituicaoFinanceira instituicaoFinanceira;

    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    public enum StatusLeilao {
        EM_ABERTO, EM_ANDAMENTO, FINALIZADO
    }
}
