package br.com.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ejb.GenericDAO;
import br.com.model.Aluguel;
import br.com.model.Aluguel.StatusAluguel;
import br.com.model.Aluguel_;
import br.com.model.Casa;
import br.com.model.Casa_;
import br.com.model.Inquilino;
import br.com.model.Inquilino_;
import br.com.model.PagamentoAluguel;
import br.com.model.Util;
import br.com.model.resultset.AluguelRS;

public class AluguelDAO extends GenericDAO<Aluguel> {
	
	public AluguelDAO() {
		super(Aluguel.class);
	}
	
	public List<AluguelRS> pesquisarRs(Long filtroCodigo, String filtroNome, String filtroCpf, StatusAluguel filtroStatus, 
			String filtroBairro, String filtroRua, Date filtroDataInicial, Date filtroDataFinal) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AluguelRS> criteria = builder.createQuery(AluguelRS.class);
		Root<Aluguel> root = criteria.from(Aluguel.class);
		
		Join<Aluguel, Inquilino>  aluInqJoin = root.join(Aluguel_.inquilino, javax.persistence.criteria.JoinType.LEFT);
		Join<Aluguel, Casa>  aluCasaJoin = root.join(Aluguel_.casa, javax.persistence.criteria.JoinType.LEFT);
		Join<Aluguel, PagamentoAluguel>  aluPagamentoJoin = root.join(Aluguel_.pagamentosAluguelList, javax.persistence.criteria.JoinType.LEFT);
		
		List<Predicate> and = new ArrayList();
		{	
			if (Util.validaStringDefault(filtroCpf)) {
				and.add(builder.equal(aluInqJoin.get(Inquilino_.cpf), filtroCpf));
			} else if (filtroCodigo != null && filtroCodigo != 0) {
				and.add(builder.equal(root.get(Aluguel_.numSequencial), filtroCodigo));
			} else {
				if (Util.validaStringDefault(filtroNome)) {
					and.add(builder.like(builder.upper(aluInqJoin.get(Inquilino_.nome)), "%" + filtroNome.toUpperCase() + "%"));
				}
				if (filtroStatus != null) {
					and.add(builder.equal(root.get(Aluguel_.status), filtroStatus));
				}
				
				if (Util.validaStringDefault(filtroBairro)) {
					and.add(builder.like(builder.upper(aluInqJoin.get(Inquilino_.nome)), "%" + filtroBairro.toUpperCase() + "%"));
				}
				
				if (Util.validaStringDefault(filtroRua)) {
					and.add(builder.like(builder.upper(aluInqJoin.get(Inquilino_.nome)), "%" + filtroRua.toUpperCase() + "%"));
				}
//				if(filtroDataInicial != null){
//					and.add(builder.greaterThanOrEqualTo(root.get(RequisicaoPso_.dtHrRequisicao), filtroDataInicial)); 
//				}
//				if(filtroDataFinal != null){
//					and.add(builder.lessThanOrEqualTo(root.get(RequisicaoPso_.dtHrRequisicao), filtroDataFinal));
//				}
			}
		}
		criteria.distinct(true);
		criteria.orderBy(builder.desc(root.get(Aluguel_.numSequencial)));
		criteria.where(and.toArray(new Predicate[and.size()]));
		criteria.select( 
			builder.construct(
				AluguelRS.class,
					root.get( Aluguel_.numSequencial ),
					aluInqJoin.get( Inquilino_.nome ),
					aluCasaJoin.get( Casa_.rua ),
					aluCasaJoin.get( Casa_.numero ),
					root.get( Aluguel_.status ),
					root.get( Aluguel_.vencimento )
				)
			);
		try{
			return entityManager.createQuery(criteria).getResultList();
		}catch(NoResultException nre){
			return null;
		}
	}

}
