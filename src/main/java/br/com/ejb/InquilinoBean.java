package br.com.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import br.com.DAO.InquilinoDAO;
import br.com.model.Inquilino;
import br.com.model.AlugueFacilUtil;
import br.com.model.resultset.InquilinoRS;

@Stateless
public class InquilinoBean extends GenericBean<Inquilino> {
	
	public InquilinoBean() {
		this.setDao(new InquilinoDAO());
	}
	
	private InquilinoDAO getInquilinoDAO(){
		return (InquilinoDAO) getDao();
	}
	
	public Inquilino pegar(long entityID) {
		return getInquilinoDAO().findById(entityID);
	}
	
	public List<InquilinoRS> pesquisarRs(Long filtroCodigo, String filtroNome, String filtroCpf) {
		return getInquilinoDAO().pesquisarRs(filtroCodigo, filtroNome, filtroCpf);
	}
	
	public Inquilino salvar(Inquilino inquilino) {
		inquilino.setDtHrCadastro(new Date());
		inquilino.setNome(inquilino.getNome().toUpperCase());
		inquilino.setNomeFiador(inquilino.getNomeFiador().toUpperCase());
		inquilino.setNomeGuerra(inquilino.getNomeGuerra().toUpperCase());
		inquilino.setProfissao(inquilino.getProfissao().toUpperCase());
		inquilino.setCpf(AlugueFacilUtil.removeMascaraGeral(inquilino.getCpf()));
		inquilino.setTelefone(AlugueFacilUtil.removeMascaraGeral(inquilino.getTelefone()));
		inquilino.setTelefoneFiador(AlugueFacilUtil.removeMascaraGeral(inquilino.getTelefoneFiador()));
		return getInquilinoDAO().update(inquilino);
	}

}
