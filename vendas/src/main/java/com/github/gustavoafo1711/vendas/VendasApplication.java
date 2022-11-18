package com.github.gustavoafo1711.vendas;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.github.gustavoafo1711.vendas.domain.entity.Cliente;
import com.github.gustavoafo1711.vendas.domain.entity.Pedido;
import com.github.gustavoafo1711.vendas.domain.repository.Clientes;
import com.github.gustavoafo1711.vendas.domain.repository.Pedidos;

@SpringBootApplication
public class VendasApplication {

	@Bean
    public CommandLineRunner init(@Autowired Clientes clientes,
    							  @Autowired Pedidos pedidos){
		
        return args -> {
            System.out.println("Salvando clientes");
            Cliente nome = new Cliente("Galadriel");
            clientes.save(nome);
            
            Pedido p = new Pedido();
            p.setCliente(nome);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));
            
            pedidos.save(p);
        
			
			/*
			 * Cliente cliente = clientes.findClienteFetchPedidos(nome.getId());
			 * System.out.println(cliente); System.out.println(cliente.getPedidos());
			 */
            
            pedidos.findByCliente(nome).forEach(System.out::println);
            
        };
    }
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
