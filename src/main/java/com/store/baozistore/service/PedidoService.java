package com.store.baozistore.service;

import com.store.baozistore.dto.ClienteDTO;
import com.store.baozistore.dto.PedidoDTO;
import com.store.baozistore.entities.Cliente;
import com.store.baozistore.entities.Pedido;
import com.store.baozistore.entities.Produto;
import com.store.baozistore.repositories.ClienteRepository;
import com.store.baozistore.repositories.PedidoRepository;
import com.store.baozistore.service.exceptions.DatabaseException;
import com.store.baozistore.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional(readOnly = true)
    public Page<PedidoDTO> findAll(Pageable pageable){
        Page<Pedido> pedidos = repository.findAll(pageable);
        return pedidos.map(p -> new PedidoDTO(p));
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id){
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto){
        Pedido pedido = new Pedido();
        pedido.setQuantidade(dto.getQuantidade());
        pedido.setProduto(modelMapper.map(dto.getProduto(), Produto.class));
        pedido.setCliente(modelMapper.map(dto.getCliente(), Cliente.class));
        pedido = repository.save(pedido);
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto){
        try{
            Pedido pedido = repository.getReferenceById(id);
            pedido.setCliente(modelMapper.map(dto.getCliente(), Cliente.class));
            pedido.setProduto(modelMapper.map(dto.getProduto(), Produto.class));
            pedido.setQuantidade(dto.getQuantidade());
            pedido = repository.save(pedido);
            return new PedidoDTO(pedido);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found.");
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found. Id = " + id);
        }
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation.");
        }
    }
}
