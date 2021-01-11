package br.com.converter;
import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.model.Casa;

@FacesConverter(value = "CasaConverter", forClass = Casa.class)
public class CasaConverter implements Converter, Serializable {

	  
	private static final long serialVersionUID = -3304040463661270514L;

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {  
        if (value != null) {  
            return this.getAttributesFrom(component).get(value);  
        }  
        return null;  
    }  
  
    @Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
  
        if (value != null && !"".equals(value)) {  
  
            Casa entity = (Casa) value;  
            // adiciona item como atributo do componente  
            this.addAttribute(component, entity);  
            Long codigo = entity.getNumSequencial();
            if (codigo != null) {  
                return String.valueOf(codigo);  
            }  
        }  
  
        return (String) value;  
    }  
  
    protected void addAttribute(UIComponent component, Casa o) {  
        String key = o.getNumSequencial().toString(); // codigo da empresa como chave neste caso  
        this.getAttributesFrom(component).put(key, o);  
    }  
  
    protected Map<String, Object> getAttributesFrom(UIComponent component) {  
        return component.getAttributes();  
    }  
  
}
