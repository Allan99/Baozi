package com.store.baozistore.service;

import com.store.baozistore.dto.ClienteDTO;
import com.store.baozistore.dto.ProdutoDTO;
import com.store.baozistore.entities.Produto;
import com.store.baozistore.repositories.ProductRepository;
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
public class ProdutoService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProdutoDTO> findAll(Pageable pageable){
        Page<Produto> produtos = repository.findAll(pageable);
        return produtos.map(prod -> new ProdutoDTO(prod));
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id){
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto){
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto = repository.save(produto);
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto){
        try{
            Produto produto = repository.getReferenceById(id);
            produto.setNome(dto.getNome());
            produto.setPreco(dto.getPreco());
            produto.setEmEstoque(dto.isEmEstoque());
            produto = repository.save(produto);
            return new ProdutoDTO(produto);
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
