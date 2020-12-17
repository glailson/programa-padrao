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
import br.com.model.Casa.StatusCasa;
import br.com.model.Util;
import br.com.model.resultset.CasaRS;

@ManagedBean
@ViewScoped
public class CasaMB extends MainMB implements Serializable {
	private static final long serialVersionUID = -5863896267107674488L;
	
	@EJB
	CasaBean casaBean;
	
	// CONTROLE DE TELA
	private Acao acaoAtual;
	private Casa casa;
	private CasaRS casaSelecionadaRs;
	private List<CasaRS> casaRsList;
	private String subTitulo, labelBtSalvar;
	//FILTROS
	private Long filtroCodigo;
	private String filtroCidade, filtroBairro, filtroRua;
	
	@PostConstruct
	private void init() { 
		navPesquisar();
	}
	
	private void render(Acao novaAcao) {
		acaoAtual = novaAcao;
		if (Acao.pesquisar.equals(acaoAtual)) {
			subTitulo = "Pesquisar Casa";
		} else {
			if (Acao.cadastrar.equals(acaoAtual)) {
				casa = new Casa();
				subTitulo = "Cadastrar Casa";
				labelBtSalvar = "Salvar";
			} else  {
				if (Acao.visualizar.equals(acaoAtual)){
					subTitulo = "Visualizar Casa";
				} else if (Acao.editar.equals(acaoAtual)) {
					subTitulo = "Editar Casa";
					labelBtSalvar = "Salvar Alterações";
				} 
				if (casa == null) {
					if (casaSelecionadaRs != null) {
						casa = casaBean.pegar(casaSelecionadaRs.getNumSequencial());
					}
				}
			}
		}
	}
	
	public void acaoSalvar () {
		casa.setDtHrCadastro(new Date());
		casa.setStatus(StatusCasa.ATIVA);
		casa = casaBean.salvar(casa);
		if (isCadastrando()) {
			addInfoMessage("Cadastro realizado com sucesso.");
		} else {
			addInfoMessage("Alterações salvas com sucesso.");
		}
		navVisualizar();
	}
	
	public void acaoPesquisar () {
		casaRsList = casaBean.pesquisarRs(filtroCodigo, filtroCidade, filtroBairro, filtroRua);
	}
	
	public void acaoLimparFiltro() {
		filtroCodigo = null;
		filtroCidade = null;
		filtroBairro = null;
		filtroRua = null;
		casaSelecionadaRs = null;
		if (Util.validaListDefault(casaRsList)) {
			casaRsList.clear();
		}
	}
	
	public void navPesquisar() {
		render(Acao.pesquisar);
	}
	
	public void navVoltarParaPesquisa() {
		casa = null;
		casaSelecionadaRs = null;
		render(Acao.pesquisar);
	}
	
	public void navCadastrar() {
		render(Acao.cadastrar);
	}
	
	public void navVisualizar() {
		render(Acao.visualizar);
	}
	
	public void navEditar() {
		render(Acao.editar);
	}
	
	public List<StatusCasa> getStatusCasaList() {
		List<StatusCasa> listaStatus = new ArrayList<>();
		for(StatusCasa itemStatus: StatusCasa.values()) {
			listaStatus.add(itemStatus);
		}
//		listaStatus.sort((StatusCasa o1, StatusCasa o2)->o1.getDescricao().compareTo(o2.getDescricao()));
		return listaStatus;
	}
	
	public boolean isPesquisando() {
		return Acao.pesquisar.equals(acaoAtual);
	}
	
	public boolean isVisualizando() {
		return Acao.visualizar.equals(acaoAtual);
	}
	
	public boolean isCadastrando() {
		return Acao.cadastrar.equals(acaoAtual);
	}
	
	public boolean isEditando() {
		return Acao.editar.equals(acaoAtual);
	}
	
	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public List<CasaRS> getCasaRsList() {
		return casaRsList;
	}

	public void setCasaRsList(List<CasaRS> casaRsList) {
		this.casaRsList = casaRsList;
	}

	public Long getFiltroCodigo() {
		return filtroCodigo;
	}

	public void setFiltroCodigo(Long filtroCodigo) {
		this.filtroCodigo = filtroCodigo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public String getFiltroCidade() {
		return filtroCidade;
	}

	public void setFiltroCidade(String filtroCidade) {
		this.filtroCidade = filtroCidade;
	}

	public String getFiltroRua() {
		return filtroRua;
	}

	public void setFiltroRua(String filtroRua) {
		this.filtroRua = filtroRua;
	}

	public String getFiltroBairro() {
		return filtroBairro;
	}

	public void setFiltroBairro(String filtroBairro) {
		this.filtroBairro = filtroBairro;
	}

	public CasaRS getCasaSelecionadaRs() {
		return casaSelecionadaRs;
	}

	public void setCasaSelecionadaRs(CasaRS casaSelecionadaRs) {
		this.casaSelecionadaRs = casaSelecionadaRs;
	}

	public String getLabelBtSalvar() {
		return labelBtSalvar;
	}

	private static enum Acao {
		cadastrar,
		pesquisar, 
		visualizar, 
		editar;
	}
}
