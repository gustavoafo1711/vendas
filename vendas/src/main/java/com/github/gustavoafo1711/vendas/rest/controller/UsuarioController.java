package com.github.gustavoafo1711.vendas.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.gustavoafo1711.vendas.domain.entity.Usuario;
import com.github.gustavoafo1711.vendas.exception.SenhaInvalidaException;
import com.github.gustavoafo1711.vendas.rest.dto.CredenciaisDTO;
import com.github.gustavoafo1711.vendas.rest.dto.TokenDTO;
import com.github.gustavoafo1711.vendas.security.jwt.JwtService;
import com.github.gustavoafo1711.vendas.service.impl.UsuarioServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioServiceImpl usuarioServiceImpl;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioServiceImpl.salvar(usuario);
	}
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO) {
		try {
			Usuario usuario = Usuario.builder()
										.login(credenciaisDTO.getLogin())
										.senha(credenciaisDTO.getSenha())
									 .build();
			UserDetails usuarioAutenticado = usuarioServiceImpl
					.autenticar(usuario);
			String token = jwtService.gerarToken(usuario);
			return new TokenDTO(usuario.getLogin(), token);
		}catch(UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
		
	}
}
