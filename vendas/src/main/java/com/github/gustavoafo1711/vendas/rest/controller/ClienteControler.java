package com.github.gustavoafo1711.vendas.rest.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.gustavoafo1711.vendas.domain.entity.Cliente;
import com.github.gustavoafo1711.vendas.domain.repository.Clientes;

@Controller
public class ClienteControler {
	
	private Clientes clientes;
	
	public ClienteControler(Clientes clientes) {
		this.clientes = clientes;
	}

	@GetMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping("/api/clientes")
	@ResponseBody
	public ResponseEntity save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clientes.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}

}
