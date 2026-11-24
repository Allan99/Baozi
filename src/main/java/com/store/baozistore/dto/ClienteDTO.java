package com.store.baozistore.dto;

import com.store.baozistore.entities.Cliente;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class ClienteDTO {

    private Long id;
    private String nome;
    private LocalDate clienteDesde;

    public ClienteDTO(Cliente entidade){
        id = entidade.getId();
        nome = entidade.getNome();
        clienteDesde = entidade.getClienteDesde();
    }

}
