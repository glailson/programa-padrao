package br.com.model;

import java.io.Serializable;
/**
 * 
 * @author glailson.leoncio
 *
 * @param <ID>
 */
public interface GenericEntity<ID> extends Serializable {
    
    public ID getNumSequencial();
    
    public void setNumSequencial(ID id);
    
}

