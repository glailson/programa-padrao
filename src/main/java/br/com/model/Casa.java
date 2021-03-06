package br.com.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbcasa")
public class Casa implements GenericEntity<Long> {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "canumsequencial")
	private Long numSequencial;
	@Size(max=100)
    @Column(name="carua")
    private String rua;
	@Size(max=100)
    @Column(name="cabairro")
    private String bairro;
	@Size(max=100)
    @Column(name="cacidade")
    private String cidade;
	@Size(max=255)
    @Column(name="caobservacao")
    private String observacao;
	@Column(name="canumero")
    private Integer numero;
	@Column(name = "cadthrcadastro")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dtHrCadastro;
	@Enumerated(EnumType.STRING)
	@Column(name = "carsituacao")
	private StatusCasa status;
	
	public Casa() {
		// TODO Auto-generated constructor stub
	}

	public Casa(Long numSequencial, String bairro, String rua, Integer numero) {
		super();
		this.numSequencial = numSequencial;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}

	@Override
	public Long getNumSequencial() {
		return numSequencial;
	}

	@Override
	public void setNumSequencial(Long numSequencial) {
		this.numSequencial = numSequencial;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDtHrCadastro() {
		return dtHrCadastro;
	}

	public StatusCasa getStatus() {
		return status;
	}

	public void setStatus(StatusCasa status) {
		this.status = status;
	}

	public void setDtHrCadastro(Date dtHrCadastro) {
		this.dtHrCadastro = dtHrCadastro;
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
		Casa other = (Casa) obj;
		if (numSequencial == null) {
			if (other.numSequencial != null)
				return false;
		} else if (!numSequencial.equals(other.numSequencial))
			return false;
		return true;
	}
	
	public static enum StatusCasa {
		ATIVA(0L,"Ativa"),
		INATIVA(1L,"Inativa");
		
		private Long numSequencial;
		private String descricao;
		
		private StatusCasa() {
		}
		
		private StatusCasa(Long numSequencial, String descricao) {
			this.numSequencial = numSequencial;
			this.descricao = descricao;
		}
		
		public Long getNumSequencial() {
			return numSequencial;
		}
		public void setNumSequencial(Long numSequencial) {
			this.numSequencial = numSequencial;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	}

}
