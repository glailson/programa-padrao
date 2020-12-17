package br.com.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ejb.GenericDAO;
import br.com.model.Casa;
import br.com.model.Casa_;
import br.com.model.Util;

public class CasaDAO extends GenericDAO<Casa> {
	
	public CasaDAO() {
		super(Casa.class);
	}
	
	public List<Casa> pesquisar(Long filtroCodigo, String filtroCidade,String filtroBairro,String filtroRua) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Casa> criteria = builder.createQuery(Casa.class);
		Root<Casa> root = criteria.from(Casa.class);
		
		List<Predicate> and = new ArrayList();
		{
			if(filtroCodigo != null && filtroCodigo != 0){
				and.add(builder.equal(root.get(Casa_.numSequencial), filtroCodigo));
			}
			if (Util.validaStringDefault(filtroCidade)) {
				and.add(builder.like(builder.upper(root.get(Casa_.cidade)), "%" + filtroCidade.toUpperCase() + "%"));
			}
			if (Util.validaStringDefault(filtroBairro)) {
				and.add(builder.like(builder.upper(root.get(Casa_.bairro)), "%" + filtroBairro.toUpperCase() + "%"));
			}
			if (Util.validaStringDefault(filtroRua)) {
				and.add(builder.like(builder.upper(root.get(Casa_.rua)), "%" + filtroRua.toUpperCase() + "%"));
			}
		}
		criteria.distinct(true);
		criteria.orderBy(builder.desc(root.get(Casa_.numSequencial)));
		criteria.where(and.toArray(new Predicate[and.size()]));
		try{
			return entityManager.createQuery(criteria).getResultList();
		}catch(NoResultException nre){
			return null;
		}
	}
}
