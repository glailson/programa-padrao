package br.com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.ejb.AluguelBean;
import br.com.ejb.PagamentoAluguelBean;
import br.com.model.Aluguel;
import br.com.model.Aluguel.StatusAluguel;
import br.com.model.Casa;
import br.com.model.Inquilino;
import br.com.model.PagamentoAluguel;
import br.com.model.Util;
import br.com.model.resultset.AluguelRS;

@ManagedBean
@ViewScoped
public class AluguelMB extends MainMB implements Serializable {
	private static final long serialVersionUID = -5863896267107674488L;
	
	@EJB
	AluguelBean aluguelBean;
	@EJB
	PagamentoAluguelBean pagamentoAluguelBean;
	// CONTROLE DE TELA
	private Acao acaoAtual;
	private Aluguel aluguel;
	private AluguelRS aluguelSelecionadoRS;
	private List<AluguelRS> aluguelRSList;
	private String subTitulo, labelBtSalvar, recebedor;
	private List<Inquilino> inquilinoList;
	private Inquilino inquilinoSelecionado;
	private List<Casa> casaList;
	private Casa casaSelecionada;
	private PagamentoAluguel pagamentoAluguel;
	private Date dataReferencia;
	private List<PagamentoAluguel> pagamentoAluguelList;
	//FILTROS
	private Long filtroCodigo;
	private String filtroCpf, filtroNome, filtroBairro, filtroRua, filtroPessoa, filtroTipoPesquisaPessoa,
		filtroBairroPopup, filtroRuaPopup;
	private StatusAluguel filtroStatus;
	private Integer filtroVencimento, filtroNumeroPopup;
	private Date filtroDataInicial,filtroDataFinal;
	
	@PostConstruct
	private void init() { 
		navPesquisar();
	}
	
	private void render(Acao novaAcao) {
		acaoAtual = novaAcao;
		filtroTipoPesquisaPessoa = "CPF";
		if (Acao.pesquisar.equals(acaoAtual)) {
			subTitulo = "Pesquisar Aluguel";
		} else {
			if (Acao.alugar.equals(acaoAtual)) {
				aluguel = new Aluguel();
				aluguel.setStatus(StatusAluguel.ATIVO);
				subTitulo = "Realizar Aluguel";
				labelBtSalvar = "Salvar";
			} else  {
				if (Acao.visualizar.equals(acaoAtual)){
					subTitulo = "Visualizar Inquilino";
				} else if (Acao.editar.equals(acaoAtual)) {
					subTitulo = "Editar Inquilino";
					labelBtSalvar = "Salvar Alterações";
				} else if (Acao.pagar.equals(acaoAtual)) {
					subTitulo = "Realizar Pagamento";
				}
				if (aluguel == null) {
					if (aluguelSelecionadoRS != null) {
						aluguel = aluguelBean.pegar(aluguelSelecionadoRS.getNumSequencial());
					}
				}
				pagamentoAluguelList = pagamentoAluguelBean.pesquisarPagamentos();
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
	
	public void abrirPopupAdicionarPagamento () {
		dataReferencia = null;
		recebedor = null;
		pagamentoAluguel = new PagamentoAluguel();
		RequestContext.getCurrentInstance().execute("PF('addPagamento').show();");
	}
	
	public void acaoSalvarPagementoAluguel () {
		boolean valido = false;
		String nomeRecebedor = null;
		if (recebedor.equals("Teste$01")) {
			valido = true;
			nomeRecebedor = "Glailson Leoncio";
		}
		if (valido) {
			pagamentoAluguel = new PagamentoAluguel();
			pagamentoAluguel.setAluguel(aluguel);
			pagamentoAluguel.setDtHrPagamento(new Date());
			pagamentoAluguel.setDtHrReferenciaPagamento(dataReferencia);
			pagamentoAluguel.setRecebedor(nomeRecebedor);
			pagamentoAluguel = pagamentoAluguelBean.salvar(pagamentoAluguel);
			pagamentoAluguelList = pagamentoAluguelBean.pesquisarPagamentos();
			addInfoMessage("Pagamento realizado com sucesso.");
		} else {
			dataReferencia = null;
			recebedor = null;			
			addErroMessage("Usuário inválido. Pagamento não realizado.");
			RequestContext.getCurrentInstance().execute("PF('addPagamento').show();");
		}
	}
	
	public void acaoSalvar () {
		boolean valido = true;
		if (aluguel.getCasa() == null) {
			valido = false;
			addErroMessage("Obrigatório selecionar Casa.");
		}
		if (aluguel.getInquilino() == null) {
			valido = false;
			addErroMessage("Obrigatório selecionar Inquilino.");
		} 
		if (valido) {
			aluguel = aluguelBean.salvar(aluguel);
			if (isAlugando()) {
				addInfoMessage("Aluguel realizado com sucesso.");
			} else {
				addInfoMessage("Alterações salvas com sucesso.");
			}
			navVisualizar();
		}
	}
	
	public void acaoPesquisarInquilino () {
		String nome = null, cpf = null;
		if(filtroTipoPesquisaPessoa.equals("NOME")){
			nome = filtroPessoa;
		}else if(filtroTipoPesquisaPessoa.equals("CPF")){
			filtroPessoa = Util.removeMascaraGeral(filtroPessoa);
			cpf = filtroPessoa;
		}
		inquilinoList = aluguelBean.pesquisarInquilino(nome, cpf);
	}
	
	public void limparCampoPesquisaPessoa(){
		filtroPessoa = "";
	}
	
	public void acaoPesquisarCasa () {
		casaList = aluguelBean.pesquisarCasa(filtroBairroPopup, filtroRuaPopup, filtroNumeroPopup);
	}
	
	public void acaoAdicionarInquilino () {
		aluguel.setInquilino(inquilinoSelecionado);
	}
	
	public void acaoAdicionarCasa () {
		Long idAluguel = aluguelBean.pesquisarSeCasaAlugada(casaSelecionada.getNumSequencial());
		if (idAluguel != null) {
			addErroMessage("Casa já alugada. Aluguel " + idAluguel);
			RequestContext.getCurrentInstance().execute("PF('pesquisarCasa').show();");
		} else {
			aluguel.setCasa(casaSelecionada);
		}
	}
	
	public void acaoLimparPesquisaInquilino() {
		filtroTipoPesquisaPessoa = "CPF";
		filtroRuaPopup = null;
		filtroNumeroPopup = null;
		if (Util.validaListDefault(inquilinoList)) {
			inquilinoList.clear();
		}
	}
	
	public void acaoLimparPesquisaCasa() {
		filtroBairroPopup = null;
		filtroPessoa = null;
		if (Util.validaListDefault(casaList)) {
			casaList.clear();
		}
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
	
	public void navPesquisar() {
		render(Acao.pesquisar);
	}
	
	public void navAlugar() {
		render(Acao.alugar);
	}
	
	public void navVisualizar() {
		render(Acao.visualizar);
	}
	
	public void navEditar() {
		render(Acao.editar);
	}
	
	public void navPagar() {
		render(Acao.pagar);
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
	
	public boolean isVisualizando() {
		return Acao.visualizar.equals(acaoAtual);
	}
	
	public boolean isEditando() {
		return Acao.editar.equals(acaoAtual);
	}
	
	public boolean isAlugando() {
		return Acao.alugar.equals(acaoAtual);
	}
	
	public boolean isPagando() {  
		return Acao.pagar.equals(acaoAtual); 
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

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public AluguelRS getAluguelSelecionadoRS() {
		return aluguelSelecionadoRS;
	}

	public void setAluguelSelecionadoRS(AluguelRS aluguelSelecionadoRS) {
		this.aluguelSelecionadoRS = aluguelSelecionadoRS;
	}

	public List<AluguelRS> getAluguelRSList() {
		return aluguelRSList;
	}

	public void setAluguelRSList(List<AluguelRS> aluguelRSList) {
		this.aluguelRSList = aluguelRSList;
	}

	public String getLabelBtSalvar() {
		return labelBtSalvar;
	}

	public String getFiltroPessoa() {
		return filtroPessoa;
	}

	public void setFiltroPessoa(String filtroPessoa) {
		this.filtroPessoa = filtroPessoa;
	}

	public String getFiltroTipoPesquisaPessoa() {
		return filtroTipoPesquisaPessoa;
	}

	public void setFiltroTipoPesquisaPessoa(String filtroTipoPesquisaPessoa) {
		this.filtroTipoPesquisaPessoa = filtroTipoPesquisaPessoa;
	}

	public List<Inquilino> getInquilinoList() {
		return inquilinoList;
	}

	public void setInquilinoList(List<Inquilino> inquilinoList) {
		this.inquilinoList = inquilinoList;
	}

	public Inquilino getInquilinoSelecionado() {
		return inquilinoSelecionado;
	}

	public void setInquilinoSelecionado(Inquilino inquilinoSelecionado) {
		this.inquilinoSelecionado = inquilinoSelecionado;
	}

	public List<Casa> getCasaList() {
		return casaList;
	}

	public void setCasaList(List<Casa> casaList) {
		this.casaList = casaList;
	}

	public Casa getCasaSelecionada() {
		return casaSelecionada;
	}

	public void setCasaSelecionada(Casa casaSelecionada) {
		this.casaSelecionada = casaSelecionada;
	}

	public String getFiltroBairroPopup() {
		return filtroBairroPopup;
	}

	public void setFiltroBairroPopup(String filtroBairroPopup) {
		this.filtroBairroPopup = filtroBairroPopup;
	}

	public String getFiltroRuaPopup() {
		return filtroRuaPopup;
	}

	public void setFiltroRuaPopup(String filtroRuaPopup) {
		this.filtroRuaPopup = filtroRuaPopup;
	}

	public Integer getFiltroNumeroPopup() {
		return filtroNumeroPopup;
	}

	public void setFiltroNumeroPopup(Integer filtroNumeroPopup) {
		this.filtroNumeroPopup = filtroNumeroPopup;
	}

	public String getRecebedor() {
		return recebedor;
	}

	public void setRecebedor(String recebedor) {
		this.recebedor = recebedor;
	}

	public Date getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public List<PagamentoAluguel> getPagamentoAluguelList() {
		return pagamentoAluguelList;
	}

	public void setPagamentoAluguelList(List<PagamentoAluguel> pagamentoAluguelList) {
		this.pagamentoAluguelList = pagamentoAluguelList;
	}

	private static enum Acao {
		alugar,
		pesquisar, 
		visualizar, 
		editar,
		pagar;
	}

}
