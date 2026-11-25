package com.store.baozistore.controllers;

import com.store.baozistore.dto.ClienteDTO;
import com.store.baozistore.dto.PedidoDTO;
import com.store.baozistore.entities.Pedido;
import com.store.baozistore.service.ClienteService;
import com.store.baozistore.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping
	public ResponseEntity<Page<PedidoDTO>> findAll(Pageable pageable){
		Page<PedidoDTO> pedidos = service.findAll(pageable);
		return ResponseEntity.ok().body(pedidos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> findById(@PathVariable Long id){
		PedidoDTO pedidoDTO = service.findById(id);
		return ResponseEntity.ok().body(pedidoDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> update(@PathVariable Long id,
											 @RequestBody PedidoDTO dto){
		PedidoDTO pedidoDTO = service.update(id, dto);
		return ResponseEntity.ok().body(pedidoDTO);
	}

	@PostMapping
	public ResponseEntity<PedidoDTO> insert(@RequestBody PedidoDTO dto){
		PedidoDTO pedidoDTO = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
		return ResponseEntity.created(uri).body(pedidoDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
