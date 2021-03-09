package br.com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("upperCaseConverter")
public class UpperCaseConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
    	if (value == null) {
    		return value;
    	} else {
    		return value.toUpperCase();
    	}
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {
    	if(value == null){  
            return "";  
        } else {
        	return value.toString();
        }
    }

}
