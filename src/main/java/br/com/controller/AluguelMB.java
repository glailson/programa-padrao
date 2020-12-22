package br.com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ejb.AluguelBean;
import br.com.model.Aluguel;
import br.com.model.Aluguel.StatusAluguel;
import br.com.model.Util;
import br.com.model.resultset.AluguelRS;

@ManagedBean
@ViewScoped
public class AluguelMB extends MainMB implements Serializable {
	private static final long serialVersionUID = -5863896267107674488L;
	
	@EJB
	AluguelBean aluguelBean;
	// CONTROLE DE TELA
	private Acao acaoAtual;
	private Aluguel aluguel;
	private AluguelRS aluguelSelecionadoRS;
	private List<AluguelRS> aluguelRSList;
	private String subTitulo, labelBtSalvar;
	//FILTROS
	private Long filtroCodigo;
	private String filtroCpf, filtroNome, filtroBairro, filtroRua;
	private StatusAluguel filtroStatus;
	private Integer filtroVencimento;
	private Date filtroDataInicial,filtroDataFinal;
	private Locale local;
	
	@PostConstruct
	private void init() { 
		local = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		navPesquisar();
	}
	
	private void render(Acao novaAcao) {
		acaoAtual = novaAcao;
		if (Acao.pesquisar.equals(acaoAtual)) {
			subTitulo = "Pesquisar Aluguel";
		} else {
			if (Acao.alugar.equals(acaoAtual)) {
				aluguel = new Aluguel();
				subTitulo = "Realizar Aluguel";
				labelBtSalvar = "Salvar";
			} else  {
				if (Acao.visualizar.equals(acaoAtual)){
					subTitulo = "Visualizar Inquilino";
				} else if (Acao.editar.equals(acaoAtual)) {
					subTitulo = "Editar Inquilino";
					labelBtSalvar = "Salvar Alterações";
				} 
				if (aluguel == null) {
					if (aluguelSelecionadoRS != null) {
						aluguel = aluguelBean.pegar(aluguelSelecionadoRS.getNumSequencial());
					}
				}
			}
		}
	}
	
	public void acaoPesquisar () {
		if (Util.validaStringDefault(filtroCpf)) {
			filtroCpf = Util.removeMascaraGeral(filtroCpf);
		}
		aluguelRSList = aluguelBean.pesquisarRs(filtroCodigo, filtroNome, filtroCpf, filtroStatus, 
			filtroBairro, filtroRua, filtroDataInicial,filtroDataFinal);
	}
	
	public void acaoLimparFiltro() {
		filtroCodigo = null;
		filtroNome = null;
		filtroCpf = null;
		filtroStatus = null;
		filtroBairro = null;
		filtroRua = null;
		aluguelSelecionadoRS = null;
		if (Util.validaListDefault(aluguelRSList)) {
			aluguelRSList.clear();
		}
	}
	
	public void navPesquisar() {
		render(Acao.pesquisar);
	}
	
	public void navAlugar() {
		render(Acao.alugar);
	}
	
	public void navVoltarParaPesquisa() {
		if (aluguel != null ) {
			aluguel = null;
		}
		if (aluguelSelecionadoRS != null) {
			aluguelSelecionadoRS = null;
		}
		render(Acao.pesquisar);
	}
	
	public List<StatusAluguel> getStatusAluguelList() {
		List<StatusAluguel> listaStatus = new ArrayList<>();
		for(StatusAluguel itemStatus: StatusAluguel.values()) {
			listaStatus.add(itemStatus);
		}
		listaStatus.sort((StatusAluguel o1, StatusAluguel o2)->o1.getDescricao().compareTo(o2.getDescricao()));
		return listaStatus;
	}
	
	public boolean isPesquisando() {  
		return Acao.pesquisar.equals(acaoAtual); 
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

	public String getFiltroCpf() {
		return filtroCpf;
	}

	public void setFiltroCpf(String filtroCpf) {
		this.filtroCpf = filtroCpf;
	}

	public String getFiltroBairro() {
		return filtroBairro;
	}

	public void setFiltroBairro(String filtroBairro) {
		this.filtroBairro = filtroBairro;
	}

	public String getFiltroRua() {
		return filtroRua;
	}

	public void setFiltroRua(String filtroRua) {
		this.filtroRua = filtroRua;
	}

	public Integer getFiltroVencimento() {
		return filtroVencimento;
	}

	public void setFiltroVencimento(Integer filtroVencimento) {
		this.filtroVencimento = filtroVencimento;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public StatusAluguel getFiltroStatus() {
		return filtroStatus;
	}

	public void setFiltroStatus(StatusAluguel filtroStatus) {
		this.filtroStatus = filtroStatus;
	}

	public Date getFiltroDataInicial() {
		return filtroDataInicial;
	}

	public void setFiltroDataInicial(Date filtroDataInicial) {
		this.filtroDataInicial = filtroDataInicial;
	}

	public Date getFiltroDataFinal() {
		return filtroDataFinal;
	}

	public void setFiltroDataFinal(Date filtroDataFinal) {
		this.filtroDataFinal = filtroDataFinal;
	}
	
	public Locale getLocal() {
        return local;
    }	

    public String getLanguage() {
        return local.getLanguage();
    }

	private static enum Acao {
		alugar,
		pesquisar, 
		visualizar, 
		editar;
	}

}
