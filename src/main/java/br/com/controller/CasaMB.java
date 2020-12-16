package br.com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ejb.CasaBean;
import br.com.model.Casa;

@ManagedBean
@ViewScoped
public class CasaMB extends MainMB implements Serializable {
	
	private static final long serialVersionUID = -5863896267107674488L;
	
	@EJB
	CasaBean casaBean;
	
	// CONTROLE DE TELA
	private Acao acaoAtual;
	private Casa casa;
	private List<Casa> casaList;
	//FILTROS
	private Long filtroCodigo;
	
	@PostConstruct
	private void init() { 
		navPesquisar();
	}
	
	private void render(Acao novaAcao) {
		acaoAtual = novaAcao;
		if (Acao.pesquisar.equals(acaoAtual)) {
			casaList = new ArrayList<Casa>();
		} else {
			if (Acao.visualizar.equals(acaoAtual)) {
			} else if (Acao.cadastrar.equals(acaoAtual)) {
				casa = new Casa();
			}
		}
	}
	
	public void acaoSalvar () {
		casa.setDtHrCadastro(new Date());
		casa.setNumero(null);
		casa = casaBean.salvar(casa);
	}
	
	public void acaoPesquisar () {
		casaList = casaBean.pesquisar(filtroCodigo);
	}
	
	public void acaoLimparFiltro() {
		filtroCodigo = null;
		if (casaList != null && casaList.size() > 0) {
			casaList.clear();
		}
	}
	
	public void navPesquisar() {
		render(Acao.pesquisar);
	}
	
	public void navCadastrar() {
		render(Acao.cadastrar);
	}
	
	public boolean isPesquisando() {
		return Acao.pesquisar.equals(acaoAtual);
	}
	
	public boolean isVisualizando() {
		return Acao.visualizar.equals(acaoAtual);
	}
	
	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public List<Casa> getCasaList() {
		return casaList;
	}

	public void setCasaList(List<Casa> casaList) {
		this.casaList = casaList;
	}

	public Long getFiltroCodigo() {
		return filtroCodigo;
	}

	public void setFiltroCodigo(Long filtroCodigo) {
		this.filtroCodigo = filtroCodigo;
	}

	private static enum Acao {
		cadastrar,
		pesquisar, 
		visualizar, 
		editar;
	}
}
