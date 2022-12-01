package com.github.gustavoafo1711.vendas.exception;

public class SenhaInvalidaException extends RuntimeException {
	public SenhaInvalidaException() {
		super("Senha inválida.");
	}
}
