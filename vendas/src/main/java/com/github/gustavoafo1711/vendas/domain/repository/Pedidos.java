package com.github.gustavoafo1711.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gustavoafo1711.vendas.domain.entity.Cliente;
import com.github.gustavoafo1711.vendas.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{
	
	List<Pedido> findByCliente(Cliente cliente);

}
