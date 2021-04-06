package com.pablodeyvid.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablodeyvid.demo.domain.Cliente;
import com.pablodeyvid.demo.services.ClienteService;

@RestController
@RequestMapping(value = "clientes")
public class ClienteController {
	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<List<Cliente>> getAll() {
		List<Cliente> lista = service.getAll();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente cli = service.findById(id);
		return ResponseEntity.ok().body(cli);
	}
}