package com.leilao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "uau_usuario_autorizacao")
public class UsuarioAutorizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uau_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usr_id", nullable = false)
    private Cliente usuario;  // Alterado para Cliente

    @ManyToOne
    @JoinColumn(name = "aut_id", nullable = false)
    private Autorizacao autorizacao;
}
