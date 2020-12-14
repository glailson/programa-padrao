package br.com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.teste.prova.controller.CidadeBean;
import br.com.teste.prova.model.Cidade;
import br.com.teste.prova.model.Estado;

@ManagedBean
@ViewScoped
public class CidadeMB extends MainMB implements Serializable {
	
	private static final long serialVersionUID = -5863896267107674488L;
	
	@EJB
	CidadeBean cidadeBean;
	
	private Cidade cidade;
	private List<Cidade> cidadelist;
	
	@PostConstruct
	private void init() { 
		render();
	}
	
	public List<javax.faces.model.SelectItem> carregarListaEstado() {
		List<javax.faces.model.SelectItem> estadoList = new ArrayList<SelectItem>();
		estadoList.add(new SelectItem("RS", "Rio Grande do Sul"));
		estadoList.add(new SelectItem("SC", "Santa Catarina"));
		estadoList.add(new SelectItem("PR", "Paraná"));
		return estadoList;

	}
	
	private void render() {
		cidadelist = new ArrayList<Cidade>();
		cidade = new Cidade();
	}
	
	public void salvar() {
		cidade.setDtHrCadastro(new Date());
		cidade.setNumSequencial(null);
		cidade.setEstado(new Estado(1L));
		if (cidade.getNome() == null || cidade.getNome() == "") {
			System.out.println("Campo 'Nome' obrigatório.");
			addErroMessage("Campo 'Nome' obrigatório.");
		} else {
			cidadelist = cidadeBean.pesquisarCidadeComMesmoNome(cidade.getNome(), 1L);
			if (cidadelist.size() != 0) {
				System.out.println("Cidade já cadastrada!");
				addErroMessage("Cidade já cadastrada.");
			} else {
				cidade.setNome(cidade.getNome().toUpperCase());
				cidade = cidadeBean.salvar(cidade);
				System.out.println("Cidade cadastrada com sucesso.");
			}
			cidade = new Cidade();
		}
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
