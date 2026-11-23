package com.store.baozistore.dto;

import com.store.baozistore.entities.Cliente;
import com.store.baozistore.entities.Produto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class PedidoDTO {

    private Long id;
    private Long clientId;
    private Long produtoId;
    private Integer quantidade;

    private Cliente cliente;

    private Produto produto;
}
