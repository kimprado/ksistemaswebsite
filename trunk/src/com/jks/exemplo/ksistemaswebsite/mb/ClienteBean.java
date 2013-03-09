package com.jks.exemplo.ksistemaswebsite.mb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.jks.exemplo.ksistemaswebsite.negocio.Cliente;
import com.jks.exemplo.ksistemaswebsite.negocio.Dependente;

@ManagedBean(name="clienteMB")
@ViewScoped
public class ClienteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente = new Cliente();
	private Dependente depen = new Dependente();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<Dependente> dependentes = new ArrayList<Dependente>();
	
	public ClienteBean() {
		
	}
	
	public String cadastrarCliente() {
		System.out.println("cadastrarCliente: " + cliente.getNome());
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity cli = new Entity("cliente");
		cli.setProperty("nome", cliente.getNome());
		cli.setProperty("endereco", cliente.getEndereco());
		cli.setProperty("telefone", cliente.getTelefone());
		datastore.put(cli);
		System.out.println("Chave primária Cliente " + cli.getKey().getId());
		
		for (Dependente dependente : dependentes) {
			Entity dep = new Entity("dependente");
			dep.setProperty("nome", dependente.getNome());
			dep.setProperty("idCliente", cli.getKey());
			datastore.put(dep);
			System.out.println("Chave primária Dependente " + dep.getKey().getId());
		}
		
		cliente = new Cliente();
		depen = new Dependente();
		dependentes.clear();
		
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

	@SuppressWarnings("deprecation")
	public List<Cliente> getClientes() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("cliente");
		PreparedQuery pq = datastore.prepare(q);
		clientes = new ArrayList<Cliente>();
		
		for (Entity c : pq.asIterable()) {
			Cliente cli = new Cliente();
			cli.setId(c.getKey());
			cli.setNome((String) c.getProperty("nome"));
			cli.setEndereco((String) c.getProperty("endereco"));
			cli.setTelefone((String) c.getProperty("telefone"));
			
			System.out.println("Cliente localizado: " + cli.getNome());
			
			Query qy = new Query("dependente");
			qy.addFilter("idCliente", Query.FilterOperator.EQUAL, c.getKey());
			
			PreparedQuery pqy = datastore.prepare(qy);
			List<Dependente> listaDependentes = new ArrayList<Dependente>();
			
			for (Entity d : pqy.asIterable()) {
				Dependente dep = new Dependente();
				dep.setNome((String) d.getProperty("nome"));
				listaDependentes.add(dep);
			}
			
			cli.setDependentes(listaDependentes);
			clientes.add(cli);
		}
		
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
