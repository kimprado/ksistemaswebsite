package com.jks.exemplo.ksistemaswebsite.negocio;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class Cliente {

	private Key id;
	private String nome;
	private String endereco;
	private String telefone;
	private List<Dependente> dependentes = new ArrayList<Dependente>();
	
	public Cliente() {
		
	}
	
	public List<Dependente> getDependentes() {
		
		return dependentes;
	}
	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
