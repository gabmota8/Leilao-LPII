package com.leilao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ins_instituicao_financeira")
public class InstituicaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ins_id")
    private Long id;

    @Column(name = "ins_nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "ins_cnpj", nullable = false, length = 20, unique = true)
    private String cnpj;
}
