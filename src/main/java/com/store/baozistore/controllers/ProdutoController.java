package com.store.baozistore.controllers;

import com.store.baozistore.dto.ClienteDTO;
import com.store.baozistore.dto.ProdutoDTO;
import com.store.baozistore.service.ClienteService;
import com.store.baozistore.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findAll(Pageable pageable){
		Page<ProdutoDTO> products = service.findAll(pageable);
		return ResponseEntity.ok().body(products);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id){
		ProdutoDTO produtoDTO = service.findById(id);
		return ResponseEntity.ok().body(produtoDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable Long id,
											 @RequestBody ProdutoDTO dto){
		ProdutoDTO produtoDTO = service.update(id, dto);
		return ResponseEntity.ok().body(produtoDTO);
	}

	@PostMapping
	public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO dto){
		ProdutoDTO produtoDTO = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
		return ResponseEntity.created(uri).body(produtoDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
