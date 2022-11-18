package com.github.gustavoafo1711.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gustavoafo1711.vendas.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{

}
