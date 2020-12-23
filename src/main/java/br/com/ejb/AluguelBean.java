package br.com.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.com.DAO.AluguelDAO;
import br.com.model.Aluguel;
import br.com.model.Inquilino;
import br.com.model.Aluguel.StatusAluguel;
import br.com.model.resultset.AluguelRS;

@Stateless
public class AluguelBean extends GenericBean<Aluguel> {
	
	public AluguelBean() {
		this.setDao(new AluguelDAO());
	}
	
	private AluguelDAO getAluguelDAO(){
		return (AluguelDAO) getDao();
	}
	
	public Aluguel pegar(long entityID) {
		return getAluguelDAO().findById(entityID);
	}
	
	public Aluguel salvar(Aluguel aluguel) {
		return getAluguelDAO().update(aluguel);
	}
	
	public List<AluguelRS> pesquisarRs(Long filtroCodigo, String filtroNome, String filtroCpf, StatusAluguel filtroStatus, 
			String filtroBairro, String filtroRua, Date filtroDataInicial, Date filtroDataFinal) {
		return getAluguelDAO().pesquisarRs(filtroCodigo, filtroNome, filtroCpf, filtroStatus, filtroBairro, filtroRua, filtroDataInicial, filtroDataFinal);
	}
	
	public List<Inquilino> pesquisarInquilino(String filtroNome, String filtroCpf) {
		return getAluguelDAO().pesquisarInquilino(filtroNome, filtroCpf);
	}

}
