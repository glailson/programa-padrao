package br.com.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbestado")
public class Estado {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "esnumsequencial")
	private Long numSequencial;
	@Size(max=150)
    @Column(name="esnome")
    private String nome;
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidadeList;
	
	public Estado() {
		// TODO Auto-generated constructor stub
	}

	public Estado(Long numSequencial) {
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

	public List<Cidade> getCidadeList() {
		return cidadeList;
	}

	public void setCidadeList(List<Cidade> cidadeList) {
		this.cidadeList = cidadeList;
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
		Estado other = (Estado) obj;
		if (numSequencial == null) {
			if (other.numSequencial != null)
				return false;
		} else if (!numSequencial.equals(other.numSequencial))
			return false;
		return true;
	}
	
}
