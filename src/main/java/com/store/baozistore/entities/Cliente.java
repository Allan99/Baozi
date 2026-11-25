package com.store.baozistore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_cliente")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate clienteDesde;

    public Cliente(Long id, String nome, LocalDate clienteDesde) {
        this.id = id;
        this.nome = nome;
        this.clienteDesde = clienteDesde;
    }
}
