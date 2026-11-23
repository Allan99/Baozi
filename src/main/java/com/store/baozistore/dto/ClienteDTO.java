package com.store.baozistore.dto;

import com.store.baozistore.entities.Pedido;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class ClienteDTO {

    private Long id;
    private String nome;
    private LocalDate clienteDesde;

    @Setter(AccessLevel.NONE)
    private Set<PedidoDTO> pedidos = new HashSet<>();
}
