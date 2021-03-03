package br.com.model;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public class AlugueFacilUtil {
	
	public static boolean validaStringDefault(String texto){
		return texto != null && !texto.trim().isEmpty();
	}
	
	static public String removeMascaraGeral(String comMascara) {
		if(comMascara != null){
			return comMascara.replace(".", "").replace("-", "").replace("/", "").replace(" ", "").replace("_", "").replace("(", "").replace(")", "");
		}
		return comMascara;
	}
	
	public static boolean validaListDefault(List<?> lista){
		return lista != null && lista.size() > 0;
	}
	
	public static boolean validarNumero(String campo){
		try{
			Long.parseLong(campo);
			return true;
		} catch (NumberFormatException e){
		}
		return false;
	}
	
	private static String verificaDigito(String num) {
		Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++){
             soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }
        if (soma % 11 == 0 | soma % 11 == 1){
	    	primDig = new Integer(0);
        } else {
        	primDig = new Integer(11 - (soma % 11));
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++) {
        	soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }
        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1) {
        	segDig = new Integer(0);
        } else {
        	segDig = new Integer(11 - (soma % 11));
        }
        return primDig.toString() + segDig.toString();
    }
	
	private static boolean isCPFPadrao(String cpf) {
		if (cpf.equals("00000000000") 
	    	  || cpf.equals("11111111111") 
	          || cpf.equals("22222222222")
	          || cpf.equals("33333333333")
	          || cpf.equals("44444444444")
	          || cpf.equals("55555555555")
	          || cpf.equals("66666666666")
	          || cpf.equals("77777777777")
	          || cpf.equals("88888888888")
	          || cpf.equals("99999999999")) {
			return true;
		}
		return false;
	}
	
	public static boolean validaCPF(String cpf) {
		cpf = removeMascaraGeral(cpf);
		if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf))
			return false;
		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) { // CPF n�o possui somente n�meros
             return false;
		}
		return verificaDigito(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
	}
	
	static public String formataCpfCnpj(String cpfCnpj) {
		cpfCnpj = cpfCnpj.trim();
		StringBuffer temp = new StringBuffer();
		if (cpfCnpj.length() == 11) {
			if(validaCPF(cpfCnpj)){
				temp.append(cpfCnpj.substring(0, 3));
				temp.append(".");
				temp.append(cpfCnpj.substring(3, 6));
				temp.append(".");
				temp.append(cpfCnpj.substring(6, 9));
				temp.append("-");
				temp.append(cpfCnpj.substring(9, 11));
			} else {
				temp.append(cpfCnpj);
			}
		} else if (cpfCnpj.length() == 14) {
			if (validaCNPJ(cpfCnpj)){
				temp.append(cpfCnpj.substring(0, 2));
				temp.append(".");
				temp.append(cpfCnpj.substring(2, 5));
				temp.append(".");
				temp.append(cpfCnpj.substring(5, 8));
				temp.append("/");
				temp.append(cpfCnpj.substring(8, 12));
				temp.append("-");
				temp.append(cpfCnpj.substring(12, 14));
			} else {
				temp.append(cpfCnpj);
			}
		} else {
			temp.append(cpfCnpj);
		}
		String retorno = temp.toString();
		return retorno;
	}
	
	static public String formataCpf(String cpf) {
		cpf = cpf.trim();
		StringBuffer temp = new StringBuffer();
		if (cpf.length() == 11) {
			temp.append(cpf.substring(0, 3));
			temp.append(".");
			temp.append(cpf.substring(3, 6));
			temp.append(".");
			temp.append(cpf.substring(6, 9));
			temp.append("-");
			temp.append(cpf.substring(9, 11));
			
		} else {
			temp.append(cpf);
		}
		String retorno = temp.toString();
		return retorno;
	}
	
	  //validar CNPJ
    public static boolean validaCNPJ(String cnpj) { 
    	cnpj = removeMascaraGeral(cnpj);
    	if (cnpj.equals("00000000000000") ||
    		cnpj.equals("11111111111111") || 
    		cnpj.equals("22222222222222") || 
    		cnpj.equals("33333333333333") || 
    		cnpj.equals("44444444444444") || 
    		cnpj.equals("55555555555555") || 
    		cnpj.equals("66666666666666") || 
    		cnpj.equals("77777777777777") || 
    		cnpj.equals("88888888888888") || 
    		cnpj.equals("99999999999999") || 
    		(cnpj.length() != 14)) 
    		return(false);
    	     	
    	char dig13, dig14; 
    	int sm, i, r, num, peso;    	
    	try {
    		sm = 0; 
    		peso = 2; 
			for (i=11; i>=0; i--) { 
				num = cnpj.charAt(i) - 48; 
				sm = sm + (num * peso);
				peso = peso + 1; 
				if (peso == 10) 
					peso = 2; 
			}    			
			r = sm % 11; 
			if ((r == 0) || (r == 1)) 
				dig13 = '0';
  			else dig13 = (char)((11-r) + 48); 
			sm = 0; peso = 2;
			for (i=12; i>=0; i--) { 
				num = cnpj.charAt(i)- 48; 
				sm = sm + (num * peso); 
				peso = peso + 1; 
				if (peso == 10)
					peso = 2; 
				} 
			r = sm % 11; 
			if ((r == 0) || (r == 1))
				dig14 = '0'; 
			else dig14 = (char)((11-r) + 48); 

			if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
				return(true); 
			else return(false);
		}
    	
    	catch (InputMismatchException erro) {
    		return(false);
    	} 	
    }
    
    static public String formataTelefone(String telefone){
		StringBuffer temp = new StringBuffer();
		if (telefone.length() >= 10) {
			temp.append("(");
			temp.append(telefone.substring(0, 2));
			temp.append(") ");
		}
		if (telefone.length() >= 10) {
			temp.append(telefone.substring(2, 7));
			temp.append("-");
			temp.append(telefone.substring(7, 10));
		}
		if (telefone.length() == 8) {
			temp.append(telefone.substring(2, 6));
			temp.append("-");
			temp.append(telefone.substring(6, 10));
		}
		if (telefone.length() > 10) {
			temp.append(telefone.substring(10, 11));
		}
		return temp.toString();
	}
    
    public static boolean betweenExclusive(Date dataInicio, Date dataFim, Date dataValidacao){
		return dataValidacao.before(dataFim) && dataValidacao.after(dataInicio);
	}

}
