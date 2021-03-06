package br.com.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.com.DAO.CasaDAO;
import br.com.model.Casa;
import br.com.model.Casa.StatusCasa;
import br.com.model.resultset.CasaRS;

@Stateless
public class CasaBean extends GenericBean<Casa> {
	
	public CasaBean() {
		this.setDao(new CasaDAO());
	}
	
	private CasaDAO getCasaDAO(){
		return (CasaDAO) getDao();
	}
	
	public Casa pegar(long entityID) {
		return getCasaDAO().findById(entityID);
	}
	
	public Casa salvar(Casa casa) {
		casa.setDtHrCadastro(new Date());
		casa.setStatus(StatusCasa.ATIVA);
		casa.setCidade(casa.getCidade().toUpperCase());
		casa.setBairro(casa.getBairro().toUpperCase());
		casa.setRua(casa.getRua().toUpperCase());
		return getCasaDAO().update(casa);
	}
	
	public List<Casa> pesquisar(Long filtroCodigo, String filtroCidade, String filtroBairro, String filtroRua) {
		return getCasaDAO().pesquisar(filtroCodigo, filtroCidade, filtroBairro, filtroRua);
	}
	
	public List<CasaRS> pesquisarRs(Long filtroCodigo, String filtroCidade,String filtroBairro,String filtroRua) {
		return getCasaDAO().pesquisarRs(filtroCodigo, filtroCidade, filtroBairro, filtroRua);
	}

}
