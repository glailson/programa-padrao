package br.com.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ejb.InquilinoBean;
import br.com.model.Inquilino;
import br.com.model.Util;
import br.com.model.resultset.InquilinoRS;

@ManagedBean
@ViewScoped
public class InquilinoMB extends MainMB implements Serializable {
	private static final long serialVersionUID = -5863896267107674488L;
	
	@EJB
	InquilinoBean inquilinoBean;
	// CONTROLE DE TELA
	private Acao acaoAtual;
	private Inquilino inquilino;
	private InquilinoRS inquilinoSelecionadoRS;
	private List<InquilinoRS> inquilinoRSList;
	private String subTitulo, labelBtSalvar;
	//FILTROS
	private Long filtroCodigo;
	private String filtroCpf, filtroNome;
	
	@PostConstruct
	private void init() { 
		navPesquisar();
	}
	
	private void render(Acao novaAcao) {
		acaoAtual = novaAcao;
		if (Acao.pesquisar.equals(acaoAtual)) {
			subTitulo = "Pesquisar Inquilino";
		} else {
			if (Acao.cadastrar.equals(acaoAtual)) {
				inquilino = new Inquilino();
				subTitulo = "Cadastrar Inquilino";
				labelBtSalvar = "Salvar";
			} else  {
				if (Acao.visualizar.equals(acaoAtual)){
					subTitulo = "Visualizar Inquilino";
				} else if (Acao.editar.equals(acaoAtual)) {
					subTitulo = "Editar Inquilino";
					labelBtSalvar = "Salvar Alterações";
				} 
				if (inquilino == null) {
					if (inquilinoSelecionadoRS != null) {
						inquilino = inquilinoBean.pegar(inquilinoSelecionadoRS.getNumSequencial());
					}
				}
			}
		}
	}
	
	public void acaoPesquisar () {
		if (Util.validaStringDefault(filtroCpf)) {
			filtroCpf = Util.removeMascaraGeral(filtroCpf);
		}
		inquilinoRSList = inquilinoBean.pesquisarRs(filtroCodigo, filtroNome, filtroCpf);
	}
	
	public void acaoLimparFiltro() {
		filtroCodigo = null;
		filtroNome = null;
		filtroCpf = null;
		inquilinoSelecionadoRS = null;
		if (Util.validaListDefault(inquilinoRSList)) {
			inquilinoRSList.clear();
		}
	}
	
	public void acaoSalvar () {
		inquilino.setDtHrCadastro(new Date());
		inquilino = inquilinoBean.salvar(inquilino);
		if (isCadastrando()) {
			addInfoMessage("Cadastro realizado com sucesso.");
		} else {
			addInfoMessage("Alterações salvas com sucesso.");
		}
		navVisualizar();
	}
	
	public void navVisualizar() {
		render(Acao.visualizar);
	}
	
	public void navVoltarParaPesquisa() {
		inquilino = null;
		inquilinoSelecionadoRS = null;
		render(Acao.pesquisar);
	}
	
	public void navCadastrar() {
		render(Acao.cadastrar);
	}
	
	public void navPesquisar() {
		render(Acao.pesquisar);
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
	
	public String getSubTitulo() {
		return subTitulo;
	}

	public Long getFiltroCodigo() {
		return filtroCodigo;
	}

	public void setFiltroCodigo(Long filtroCodigo) {
		this.filtroCodigo = filtroCodigo;
	}

	public String getFiltroCpf() {
		return filtroCpf;
	}

	public void setFiltroCpf(String filtroCpf) {
		this.filtroCpf = filtroCpf;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}

	public InquilinoRS getInquilinoSelecionadoRS() {
		return inquilinoSelecionadoRS;
	}

	public void setInquilinoSelecionadoRS(InquilinoRS inquilinoSelecionadoRS) {
		this.inquilinoSelecionadoRS = inquilinoSelecionadoRS;
	}

	public List<InquilinoRS> getInquilinoRSList() {
		return inquilinoRSList;
	}

	public void setInquilinoRSList(List<InquilinoRS> inquilinoRSList) {
		this.inquilinoRSList = inquilinoRSList;
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
