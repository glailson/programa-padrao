package br.com.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.ejb.InquilinoBean;

@ManagedBean
@ViewScoped
public class InquilinoMB extends MainMB implements Serializable {
	private static final long serialVersionUID = -5863896267107674488L;
	
	@EJB
	InquilinoBean inquilinoBean;
	
	// CONTROLE DE TELA
	private Acao acaoAtual;
	private String subTitulo, labelBtSalvar;
	
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
				subTitulo = "Cadastrar Inquilino";
				labelBtSalvar = "Salvar";
			} else  {
				if (Acao.visualizar.equals(acaoAtual)){
					subTitulo = "Visualizar Inquilino";
				} else if (Acao.editar.equals(acaoAtual)) {
					subTitulo = "Editar Inquilino";
					labelBtSalvar = "Salvar Alterações";
				} 
//				if (casa == null) {
//					if (casaSelecionadaRs != null) {
//						casa = casaBean.pegar(casaSelecionadaRs.getNumSequencial());
//					}
//				}
			}
		}
	}
	
	public void navPesquisar() {
		render(Acao.pesquisar);
	}
	
	public String getSubTitulo() {
		return subTitulo;
	}

	private static enum Acao {
		cadastrar,
		pesquisar, 
		visualizar, 
		editar;
	}

}
