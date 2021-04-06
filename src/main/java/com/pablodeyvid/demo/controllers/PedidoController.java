package com.pablodeyvid.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablodeyvid.demo.domain.Pedido;
import com.pablodeyvid.demo.services.PedidoService;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoController {
	@Autowired
	private PedidoService service;

	@GetMapping
	public ResponseEntity<List<Pedido>> getAll() {
		List<Pedido> lista = service.getAll();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		Pedido ped = service.findById(id);
		return ResponseEntity.ok().body(ped);
	}
}