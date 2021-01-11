package br.com.DAO;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ejb.GenericDAO;
import br.com.model.PagamentoAluguel;
import br.com.model.PagamentoAluguel_;

public class PagamentoAluguelDAO extends GenericDAO<PagamentoAluguel> {
	
	public PagamentoAluguelDAO() {
		super(PagamentoAluguel.class);
	}
	
	public List<PagamentoAluguel> pesquisarPagamentos() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PagamentoAluguel> criteria = builder.createQuery(PagamentoAluguel.class);
		Root<PagamentoAluguel> root = criteria.from(PagamentoAluguel.class);
		
		criteria.distinct(true);
		criteria.orderBy(builder.desc(root.get(PagamentoAluguel_.numSequencial)));
		try{
			return entityManager.createQuery(criteria).getResultList();
		}catch(NoResultException nre){
			return null;
		}
	}

}
