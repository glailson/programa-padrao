package br.com.ejb;

import java.util.List;

import javax.ejb.Stateless;

import br.com.DAO.PagamentoAluguelDAO;
import br.com.model.PagamentoAluguel;

@Stateless
public class PagamentoAluguelBean extends GenericBean<PagamentoAluguel> {
	
	public PagamentoAluguelBean() {
		this.setDao(new PagamentoAluguelDAO());
	}
	
	private PagamentoAluguelDAO getPagamentoAluguelDAO(){
		return (PagamentoAluguelDAO) getDao();
	}
	
	public PagamentoAluguel salvar(PagamentoAluguel pagamentoAluguel) {
		return getPagamentoAluguelDAO().update(pagamentoAluguel);
	}
	
	public List<PagamentoAluguel> pesquisarPagamentos() {
		return getPagamentoAluguelDAO().pesquisarPagamentos();
	}
}
