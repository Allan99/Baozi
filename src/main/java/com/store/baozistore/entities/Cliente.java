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
    @Column(name = "cliente_desde")
    private LocalDate clienteDesde;

    @OneToMany(mappedBy = "cliente")
    @Setter(AccessLevel.NONE)
    private Set<Pedido> pedidos = new HashSet<>();
}
