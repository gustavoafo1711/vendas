package com.github.gustavoafo1711.vendas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.github.gustavoafo1711.vendas.validation.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
	@NotNull(message = "{campo.codigo-cliente.obrigatorio}")
	private Integer cliente;
	
	@NotNull(message = "{campo.total-pedido.obrigatorio}")
	private BigDecimal total;
	
	@NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
	private List<ItemPedidoDTO> items;

}
