package com.github.gustavoafo1711.vendas.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.gustavoafo1711.vendas.domain.entity.Usuario;
import com.github.gustavoafo1711.vendas.domain.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByLogin(username)
			.orElseThrow(() -> new UsernameNotFoundException
									("Usuário não encontrado na base de dados."));
		
		String [] roles = usuario.isAdmin() ? 
						new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return User
				.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
	}

	
	
	
	
}