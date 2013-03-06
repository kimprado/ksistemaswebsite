package com.jks.exemplo.ksistemaswebsite.negocio;

import java.io.Serializable;

public class Dependente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;

	public Dependente() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
