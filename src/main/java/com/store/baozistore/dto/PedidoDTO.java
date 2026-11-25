package com.store.baozistore.dto;

import com.store.baozistore.entities.Cliente;
import com.store.baozistore.entities.Pedido;
import com.store.baozistore.entities.Produto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class PedidoDTO {

    private Long id;
    private ClienteDTO cliente;
    private ProdutoDTO produto;
    private Integer quantidade;

    public PedidoDTO(Pedido entidade){
        id = entidade.getId();
        cliente = new ClienteDTO(entidade.getCliente());
        produto = new ProdutoDTO(entidade.getProduto());
        quantidade = entidade.getQuantidade();
    }
}
