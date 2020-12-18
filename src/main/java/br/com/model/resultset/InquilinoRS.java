package br.com.model.resultset;

public class InquilinoRS {
	
	private Long numSequencial;
    private String nome;
    private String cpf;
    private String telefone;
    
    public InquilinoRS() {
		// TODO Auto-generated constructor stub
	}

	public InquilinoRS(Long numSequencial, String nome, String cpf, String telefone) {
		super();
		this.numSequencial = numSequencial;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
