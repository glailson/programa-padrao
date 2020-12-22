package br.com.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.ejb.GenericDAO;
import br.com.model.Inquilino;
import br.com.model.Inquilino_;
import br.com.model.Util;
import br.com.model.resultset.InquilinoRS;

public class InquilinoDAO extends GenericDAO<Inquilino> {
	
	public InquilinoDAO() {
		super(Inquilino.class);
	}
	
	public List<InquilinoRS> pesquisarRs(Long filtroCodigo, String filtroNome, String filtroCpf) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<InquilinoRS> criteria = builder.createQuery(InquilinoRS.class);
		Root<Inquilino> root = criteria.from(Inquilino.class);
		
		List<Predicate> and = new ArrayList();
		{
			if(filtroCodigo != null && filtroCodigo != 0){
				and.add(builder.equal(root.get(Inquilino_.numSequencial), filtroCodigo));
			}
			if (Util.validaStringDefault(filtroNome)) {
				and.add(builder.like(builder.upper(root.get(Inquilino_.nome)), "%" + filtroNome.toUpperCase() + "%"));
			}
			if (Util.validaStringDefault(filtroCpf)) {
				and.add(builder.like(builder.upper(root.get(Inquilino_.cpf)), "%" + filtroNome.toUpperCase() + "%"));
			}
		}
		criteria.distinct(true);
		criteria.orderBy(builder.desc(root.get(Inquilino_.numSequencial)));
		criteria.where(and.toArray(new Predicate[and.size()]));
		criteria.select( 
			builder.construct(
				InquilinoRS.class,
					root.get( Inquilino_.numSequencial ),
					root.get( Inquilino_.nome ),
					root.get( Inquilino_.cpf ),
					root.get( Inquilino_.telefone )
				)
			);
		try{
			return entityManager.createQuery(criteria).getResultList();
		}catch(NoResultException nre){
			return null;
		}
	}
}
