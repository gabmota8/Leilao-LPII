package com.leilao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lan_lance")
public class Lance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lan_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pro_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "usr_id", nullable = false)
    private Cliente cliente;

    @Column(name = "lan_valor", nullable = false)
    private Double valor;

    @Column(name = "lan_data_hora", nullable = false)
    private LocalDateTime dataHora;
}
