package com.github.gustavoafo1711.vendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.gustavoafo1711.vendas.domain.entity.Cliente;
import com.github.gustavoafo1711.vendas.domain.entity.ItemPedido;
import com.github.gustavoafo1711.vendas.domain.entity.Pedido;
import com.github.gustavoafo1711.vendas.domain.entity.Produto;
import com.github.gustavoafo1711.vendas.domain.enums.StatusPedido;
import com.github.gustavoafo1711.vendas.domain.repository.Clientes;
import com.github.gustavoafo1711.vendas.domain.repository.ItemsPedido;
import com.github.gustavoafo1711.vendas.domain.repository.Pedidos;
import com.github.gustavoafo1711.vendas.domain.repository.Produtos;
import com.github.gustavoafo1711.vendas.exception.RegraNegocioException;
import com.github.gustavoafo1711.vendas.rest.dto.ItemPedidoDTO;
import com.github.gustavoafo1711.vendas.rest.dto.PedidoDTO;
import com.github.gustavoafo1711.vendas.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{
	
	private final Pedidos pedidosRepository;
	private final Clientes clientesRepository;
	private final Produtos produtosRepository;
	private final ItemsPedido itemsPedidoRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientesRepository.findById(idCliente)
					.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		pedidosRepository.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		return pedido;
	}
	

	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if(items.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
		}
		
		return items.stream().map(dto -> {
			Integer idProduto = dto.getProduto();
			Produto produto = produtosRepository.findById(idProduto)
				.orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));
			
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
			
		}).collect(Collectors.toList());
		
	}


	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		
		return pedidosRepository.findByIdFetchItens(id);
	}
	
}
