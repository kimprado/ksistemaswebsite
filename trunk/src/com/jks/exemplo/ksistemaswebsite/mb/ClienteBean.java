package com.jks.exemplo.ksistemaswebsite.mb;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.jks.exemplo.ksistemaswebsite.negocio.Cliente;
import com.jks.exemplo.ksistemaswebsite.negocio.Dependente;

@ManagedBean(name="clienteMB")
@ApplicationScoped
public class ClienteBean {
	
	private Cliente cliente = new Cliente();
	private Dependente depen = new Dependente();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<Dependente> dependentes = new ArrayList<Dependente>();
	
	public ClienteBean() {
		
	}
	
	public String cadastrarCliente() {
		System.out.println("Testando JSF 2 e GAE");
		cliente.setDependentes(dependentes);
		clientes.add(cliente);
		
		cliente = new Cliente();
		depen = new Dependente();
		dependentes = new ArrayList<Dependente>();
		
		return "index.jsp";
	}
	
	public void cadastrarDependente() {
		dependentes.add(depen);
		depen = new Dependente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Dependente getDepen() {
		return depen;
	}

	public void setDepen(Dependente depen) {
		this.depen = depen;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}
}
