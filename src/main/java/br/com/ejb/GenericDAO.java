package br.com.ejb;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.teste.prova.model.GenericEntity;

public class GenericDAO<T extends GenericEntity<Long>> {
    
    public EntityManager entityManager;
    
    private Class<T> entityClass;
    
    public GenericDAO(Class<T> entityClass){
        this.entityClass = entityClass;
    }
    
    protected void delete(Object id, Class<T> classe){
        T entityToBeRemoved = entityManager.getReference(classe, id);
        
        entityManager.remove(entityToBeRemoved);
    }
    
    public T findById(long entityID) {
        return entityManager.find(entityClass, entityID);
    }
    
    
    public void save (T entity){
        entityManager.persist(entity);
    }
    
    @Deprecated
    public T update (T entity){
        return entityManager.merge(entity);
    }
    
    public T updateDireto(T entity){
    	return entityManager.merge(entity);
    }
	
    
    public <T> T carregarSemSessao(Class<T> clazz, Object entityId) {
        T temp = entityManager.find(clazz, entityId);
        entityManager.detach(temp);
        return temp;
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> findAll(){
        CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }
    
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters){
        T result = null;
 
        try {
            Query query = entityManager.createNamedQuery(namedQuery);
 
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            result = (T) query.getSingleResult();
 
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
 
        return result;
    }
    
    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    protected EntityManager getEntityManager(){
        return this.entityManager;
    }
    
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    protected Class<T> getEntityClass() {
        if (entityClass == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            Object o = type.getActualTypeArguments()[0];
            this.entityClass = (Class<T>) o;
        }
        return entityClass;
    }
}

