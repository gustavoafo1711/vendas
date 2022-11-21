package com.github.gustavoafo1711.vendas.rest.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.gustavoafo1711.vendas.domain.entity.Produto;
import com.github.gustavoafo1711.vendas.domain.repository.Produtos;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	private Produtos produtosRepository;

	public ProdutoController(Produtos produtosRepository) {
		this.produtosRepository = produtosRepository;
	}
	
	@GetMapping("{id}")
	public Produto getProdutoById(@PathVariable Integer id) {
		return produtosRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, 
											"Produto não encontrado."));
		
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Produto save(@RequestBody Produto produto) {
		return produtosRepository.save(produto);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtosRepository.findById(id)
					.map(produto -> {
						produtosRepository.delete(produto);
						return produto;
					})
					.orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
												"Produto não encontrado."));

	}

	@PutMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Produto produto) {
		produtosRepository.findById(id).map(produtoExistente -> {
			produto.setId(produtoExistente.getId());
			produtosRepository.save(produto);
			return produtoExistente;
		}).orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
				"Produto não encontrado."));
	}
	
	@GetMapping
	public List<Produto> find(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		return produtosRepository.findAll(example);

	}
}
