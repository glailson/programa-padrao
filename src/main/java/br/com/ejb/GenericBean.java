package br.com.ejb;


import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.model.GenericEntity;

@Stateless
@LocalBean
public abstract class GenericBean<T extends GenericEntity<Long>> {
	
	@PersistenceContext(unitName = "ProjetoPU")
    private EntityManager entityManager;
	
	private GenericDAO<T> dao;

    public void setDao(GenericDAO<T> dao) {
        this.dao = dao;
    }

    @PostConstruct
    public void initBean() {
        dao.setEntityManager(entityManager);
    }

    public GenericDAO<T> getDao() {
        return dao;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public boolean validarString(String string){
        if(string == null) return false;
        else return !string.trim().equals("");
    }
    
    public boolean validarString(String string, int minimo){
        if(!validarString(string)){
    		return string.length() > minimo;
        }
    	return false;
    }

	public void limparCacheHibernate() {
		entityManager.clear();
	}
}

