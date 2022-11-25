package com.github.gustavoafo1711.vendas.service;

import java.util.Optional;

import com.github.gustavoafo1711.vendas.domain.entity.Pedido;
import com.github.gustavoafo1711.vendas.domain.enums.StatusPedido;
import com.github.gustavoafo1711.vendas.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
	
	void atualizaStatus(Integer id, StatusPedido statusPedido);

}
