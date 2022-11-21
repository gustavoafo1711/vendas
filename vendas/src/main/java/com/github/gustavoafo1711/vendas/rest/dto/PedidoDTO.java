package com.github.gustavoafo1711.vendas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {
	
	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;

}
