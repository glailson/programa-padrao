package br.com.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ejb.GenericDAO;
import br.com.model.Cidade;
import br.com.model.Cidade_;
import br.com.model.Estado;
import br.com.model.Estado_;

public class CidadeDAO extends GenericDAO<Cidade> {
	
	public CidadeDAO() {
		super(Cidade.class);
	}
	
	public List<Cidade> pesquisarCidadeComMesmoNome(String nomeCidade, Long idEstado) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		Join<Cidade, Estado> cidEstJoin = root.join(Cidade_.estado, javax.persistence.criteria.JoinType.LEFT);
		List<Predicate> and = new ArrayList<>();
		{
			if (nomeCidade != null && nomeCidade != "") {
				and.add(builder.equal(root.get(Cidade_.nome), nomeCidade.toUpperCase()));
			} 
			if (idEstado != null && idEstado != 0L) {
				and.add(builder.equal(cidEstJoin.get(Estado_.numSequencial), idEstado));
			} 
		}
		criteria.orderBy(builder.desc(root.get(Cidade_.numSequencial)));
		criteria.where(and.toArray(new Predicate[and.size()]));
		try {
			return entityManager.createQuery( criteria ).getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
