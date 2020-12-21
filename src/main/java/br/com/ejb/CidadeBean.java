package br.com.ejb;

import java.util.List;

import javax.ejb.Stateless;

import br.com.DAO.CidadeDAO;
import br.com.model.Cidade;

@Stateless
public class CidadeBean extends GenericBean<Cidade> {
	
	public CidadeBean(){
		this.setDao(new CidadeDAO());
	}
	private CidadeDAO getCidadeDAO(){
		return (CidadeDAO) getDao();
	}
	
	public Cidade pegar(long entityID) {
		return getCidadeDAO().findById(entityID);
	}
	
	public Cidade salvar(Cidade cidade) {
		return getCidadeDAO().update(cidade);
	}
	
	public List<Cidade> pesquisarCidadeComMesmoNome(String nomeCidade, Long idEstado) {
		return getCidadeDAO().pesquisarCidadeComMesmoNome(nomeCidade, idEstado);
	}
	
}
