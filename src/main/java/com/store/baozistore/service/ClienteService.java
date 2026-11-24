package com.store.baozistore.service;

import com.store.baozistore.dto.ClienteDTO;
import com.store.baozistore.entities.Cliente;
import com.store.baozistore.repositories.ClienteRepository;
import com.store.baozistore.service.exceptions.DatabaseException;
import com.store.baozistore.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAll(Pageable pageable){
        Page<Cliente> clientes = repository.findAll(pageable);
        return clientes.map(cli -> new ClienteDTO(cli));
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id){
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setClienteDesde(dto.getClienteDesde());
        cliente = repository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto){
        try{
            Cliente cliente = repository.getReferenceById(id);
            cliente.setNome(dto.getNome());
            cliente.setClienteDesde(dto.getClienteDesde());
            cliente = repository.save(cliente);
            return new ClienteDTO(cliente);
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
