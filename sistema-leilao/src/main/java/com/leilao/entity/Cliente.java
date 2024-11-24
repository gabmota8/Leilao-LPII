package com.leilao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr_usuario")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "usr_email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "usr_senha", nullable = false, columnDefinition = "TEXT")
    private String senha;

    
}
