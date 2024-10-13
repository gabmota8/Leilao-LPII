package com.leilao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("VEICULO")
public class Veiculo extends Produto {
    private String placa;
    private String modelo;
    private String fabricante;
    private int ano;    
}
