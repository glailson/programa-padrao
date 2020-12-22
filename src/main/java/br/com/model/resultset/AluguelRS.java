package br.com.model.resultset;

import br.com.model.Aluguel.StatusAluguel;

public class AluguelRS {
	
	private Long numSequencial;
	private String nome;
	private String bairro;
	private String rua;
	private Integer numero;
	private StatusAluguel status;
	private Integer vencimento;
	private Double valor;
	
	public AluguelRS() {
		// TODO Auto-generated constructor stub
	}
	
	

	public AluguelRS(Long numSequencial, String nome, String rua, Integer numero, StatusAluguel status,
			Integer vencimento) {
		super();
		this.numSequencial = numSequencial;
		this.nome = nome;
		this.rua = rua;
		this.numero = numero;
		this.status = status;
		this.vencimento = vencimento;
	}



	public AluguelRS(Long numSequencial) {
		super();
		this.numSequencial = numSequencial;
	}

	public Long getNumSequencial() {
		return numSequencial;
	}

	public void setNumSequencial(Long numSequencial) {
		this.numSequencial = numSequencial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public StatusAluguel getStatus() {
		return status;
	}

	public void setStatus(StatusAluguel status) {
		this.status = status;
	}

	public Integer getVencimento() {
		return vencimento;
	}

	public void setVencimento(Integer vencimento) {
		this.vencimento = vencimento;
	}
	
}
