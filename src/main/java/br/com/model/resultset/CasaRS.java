package br.com.model.resultset;

public class CasaRS {
	
	private Long numSequencial;
	private String cidade;
	private String bairro;
    private String rua;
    private Integer numero;
    
    public CasaRS() {
		// TODO Auto-generated constructor stub
	}

	public CasaRS(Long numSequencial, String cidade, String bairro, String rua, Integer numero) {
		super();
		this.numSequencial = numSequencial;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}

	public Long getNumSequencial() {
		return numSequencial;
	}

	public void setNumSequencial(Long numSequencial) {
		this.numSequencial = numSequencial;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
