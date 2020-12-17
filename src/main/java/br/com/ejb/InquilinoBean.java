package br.com.ejb;

import javax.ejb.Stateless;

import br.com.DAO.InquilinoDAO;
import br.com.model.Inquilino;

@Stateless
public class InquilinoBean extends GenericBean<Inquilino> {
	
	public InquilinoBean() {
		this.setDao(new InquilinoDAO());
	}
	
	private InquilinoDAO getCasaDAO(){
		return (InquilinoDAO) getDao();
	}

}
