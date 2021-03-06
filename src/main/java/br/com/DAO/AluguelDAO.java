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
import br.com.model.AlugueFacilUtil;
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
			if (AlugueFacilUtil.validaStringDefault(filtroCpf)) {
				and.add(builder.equal(aluInqJoin.get(Inquilino_.cpf), filtroCpf));
			} else if (filtroCodigo != null && filtroCodigo != 0) {
				and.add(builder.equal(root.get(Aluguel_.numSequencial), filtroCodigo));
			} else {
				if (AlugueFacilUtil.validaStringDefault(filtroNome)) {
					and.add(builder.like(builder.upper(aluInqJoin.get(Inquilino_.nome)), "%" + filtroNome.toUpperCase() + "%"));
				}
				if (filtroStatus != null) {
					and.add(builder.equal(root.get(Aluguel_.status), filtroStatus));
				}
				
				if (AlugueFacilUtil.validaStringDefault(filtroBairro)) {
					and.add(builder.like(builder.upper(aluInqJoin.get(Inquilino_.nome)), "%" + filtroBairro.toUpperCase() + "%"));
				}
				
				if (AlugueFacilUtil.validaStringDefault(filtroRua)) {
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
					aluInqJoin.get( Inquilino_.nomeGuerra ),
					aluCasaJoin.get( Casa_.bairro ),
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
	
	public List<Inquilino> pesquisarInquilino(String filtroNome, String filtroCpf) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Inquilino> criteria = builder.createQuery(Inquilino.class);
		Root<Inquilino> root = criteria.from(Inquilino.class);
		
		List<Predicate> and = new ArrayList();
		{	
			if (AlugueFacilUtil.validaStringDefault(filtroNome)) {
				and.add(builder.equal(root.get(Inquilino_.cpf), filtroCpf));
			} 
			if (AlugueFacilUtil.validaStringDefault(filtroCpf)) {
				and.add(builder.like(builder.upper(root.get(Inquilino_.nome)), "%" + filtroNome.toUpperCase() + "%"));
			}
		}
		criteria.distinct(true);
		criteria.orderBy(builder.desc(root.get(Inquilino_.numSequencial)));
		criteria.where(and.toArray(new Predicate[and.size()]));
		criteria.select( 
			builder.construct(
				Inquilino.class,
					root.get( Inquilino_.numSequencial ),
					root.get( Inquilino_.nome ),
					root.get( Inquilino_.cpf )
				)
			);
		try{
			return entityManager.createQuery(criteria).getResultList();
		}catch(NoResultException nre){
			return null;
		}
	}
	
	public List<Casa> pesquisarCasa(String filtroBairro, String filtroRua, Integer filtroNumero) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Casa> criteria = builder.createQuery(Casa.class);
		Root<Casa> root = criteria.from(Casa.class);
		
		List<Predicate> and = new ArrayList();
		{	
			if (AlugueFacilUtil.validaStringDefault(filtroBairro)) {
				and.add(builder.like(builder.upper(root.get(Casa_.bairro)), "%" + filtroBairro.toUpperCase() + "%"));
			} 
			if (AlugueFacilUtil.validaStringDefault(filtroRua)) {
				and.add(builder.like(builder.upper(root.get(Casa_.rua)), "%" + filtroRua.toUpperCase() + "%"));
			}
			if (filtroNumero != null) {
				and.add(builder.equal(root.get(Casa_.numero), filtroNumero));
			}
		}
		criteria.distinct(true);
		criteria.orderBy(builder.desc(root.get(Casa_.numSequencial)));
		criteria.where(and.toArray(new Predicate[and.size()]));
		criteria.select( 
			builder.construct(
				Casa.class,
					root.get( Casa_.numSequencial ),
					root.get( Casa_.bairro ),
					root.get( Casa_.rua ),
					root.get( Casa_.numero )
				)
			);
		try{
			return entityManager.createQuery(criteria).getResultList();
		}catch(NoResultException nre){
			return null;
		}
	}
	
	public Long pesquisarSeCasaAlugada(Long idCasa) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Aluguel> root = criteria.from(Aluguel.class);
		
		Join<Aluguel, Casa>  aluCasaJoin = root.join(Aluguel_.casa, javax.persistence.criteria.JoinType.LEFT);
		List<Predicate> and = new ArrayList<>();
		{
			if (idCasa != null && idCasa != 0) {
				and.add(builder.equal(aluCasaJoin.get(Casa_.numSequencial), idCasa));
			}
			and.add(builder.equal(root.get(Aluguel_.status), StatusAluguel.ATIVO));
		}
		criteria.select(root.get(Aluguel_.numSequencial));
		criteria.orderBy(builder.desc(root.get(Aluguel_.numSequencial)));
		criteria.where(and.toArray(new Predicate[and.size()]));
		try{
			return getEntityManager().createQuery(criteria).setMaxResults(1).getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}

}
