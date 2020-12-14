package br.com.teste.prova.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbcidade")
public class Cidade implements GenericEntity<Long> {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cinumsequencial")
	private Long numSequencial;
	@Size(max=150)
    @Column(name="cinome")
    private String nome;
	@JoinColumn(name = "ciestado", referencedColumnName = "esnumsequencial")
    @ManyToOne
	private Estado estado;
	@Column(name = "cidthrcadastro")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dtHrCadastro;
    
	public Cidade() {
		// TODO Auto-generated constructor stub
	}

	public Cidade(Long numSequencial) {
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getDtHrCadastro() {
		return dtHrCadastro;
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
		Cidade other = (Cidade) obj;
		if (numSequencial == null) {
			if (other.numSequencial != null)
				return false;
		} else if (!numSequencial.equals(other.numSequencial))
			return false;
		return true;
	}
	
}
