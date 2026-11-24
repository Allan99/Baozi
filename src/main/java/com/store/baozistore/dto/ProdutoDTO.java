package com.store.baozistore.dto;

import com.store.baozistore.entities.Produto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class ProdutoDTO {

    private Long id;
    private String nome;
    private BigDecimal preco;
    private boolean emEstoque;

    public ProdutoDTO(Produto entidade){
        id = entidade.getId();
        nome = entidade.getNome();
        preco = entidade.getPreco();
        emEstoque = entidade.isEmEstoque();
    }
}
