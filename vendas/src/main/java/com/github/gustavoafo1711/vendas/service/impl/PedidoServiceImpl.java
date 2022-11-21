package com.github.gustavoafo1711.vendas.service.impl;

import org.springframework.stereotype.Service;

import com.github.gustavoafo1711.vendas.domain.repository.Pedidos;
import com.github.gustavoafo1711.vendas.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	private Pedidos pedidosRepository;

	public PedidoServiceImpl(Pedidos pedidosRepository) {
		this.pedidosRepository = pedidosRepository;
	}
	

}
