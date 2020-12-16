package br.com.ejb;

import java.util.List;

import javax.ejb.Stateless;

import br.com.DAO.CasaDAO;
import br.com.model.Casa;

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
		return getCasaDAO().update(casa);
	}
	
	public List<Casa> pesquisar(Long filtroCodigo) {
		return getCasaDAO().pesquisar(filtroCodigo);
	}

}
