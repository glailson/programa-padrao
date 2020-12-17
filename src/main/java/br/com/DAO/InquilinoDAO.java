package br.com.DAO;

import br.com.ejb.GenericDAO;
import br.com.model.Inquilino;

public class InquilinoDAO extends GenericDAO<Inquilino> {
	
	public InquilinoDAO() {
		super(Inquilino.class);
	}
}
