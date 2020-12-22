package br.com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.model.Util;

@FacesConverter("CpfCnpjConverter")
public class CpfCnpjConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
    	if(value != null){  
            return Util.removeMascaraGeral(value);  
        } 
    	return value;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {
    	if(value == null){  
            return "";  
        } 
        return Util.formataCpfCnpj(value.toString());
    }

}