package br.com.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbinquilino")
public class Inquilino implements GenericEntity<Long> {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "innumsequencial")
	private Long numSequencial;
	@Size(max=100)
    @Column(name="innome")
    private String nome;
	@Size(max=100)
    @Column(name="innomeguerra")
    private String nomeGuerra;
	@Size(max=25)
    @Column(name="incpf")
    private String cpf;
	@Size(max=255)
    @Column(name="inobservacao")
    private String observacao;
	@Size(max=25)
    @Column(name="intelefone")
    private String telefone;
	@Size(max=60)
    @Column(name="inprofissao")
    private String profissao;
	@Column(name = "indthrcadastro")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dtHrCadastro;
	@Size(max=100)
    @Column(name="innomefiador")
    private String nomeFiador;
	@Size(max=25)
    @Column(name="intelefonefiador")
    private String telefoneFiador;
	
	public Inquilino() {
		// TODO Auto-generated constructor stub
	}

	public Inquilino(Long numSequencial, String nome, String cpf) {
		super();
		this.numSequencial = numSequencial;
		this.nome = nome;
		this.cpf = cpf;
	}

	@Override
	public Long getNumSequencial() {
		return numSequencial;
	}

	@Override
	public void setNumSequencial(Long numSequencial) {
		this.numSequencial = numSequencial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Date getDtHrCadastro() {
		return dtHrCadastro;
	}

	public void setDtHrCadastro(Date dtHrCadastro) {
		this.dtHrCadastro = dtHrCadastro;
	}

	public String getNomeFiador() {
		return nomeFiador;
	}

	public void setNomeFiador(String nomeFiador) {
		this.nomeFiador = nomeFiador;
	}

	public String getTelefoneFiador() {
		return telefoneFiador;
	}

	public void setTelefoneFiador(String telefoneFiador) {
		this.telefoneFiador = telefoneFiador;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numSequencial == null) ? 0 : numSequencial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inquilino other = (Inquilino) obj;
		if (numSequencial == null) {
			if (other.numSequencial != null)
				return false;
		} else if (!numSequencial.equals(other.numSequencial))
			return false;
		return true;
	}
}
