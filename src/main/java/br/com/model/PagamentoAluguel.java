package br.com.model;

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

@Entity
@Table(name="tbpagamentoaluguel")
public class PagamentoAluguel implements GenericEntity<Long> {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "panumsequencial")
	private Long numSequencial;
	@ManyToOne
	@JoinColumn(name = "paaluguel", referencedColumnName = "alnumsequencial")
	private Aluguel aluguel;
	@Column(name = "padthrpagamento")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dtHrPagamento;
	@Column(name = "padthrreferenciapagamento")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dtHrReferenciaPagamento;
	
	public PagamentoAluguel() {
		// TODO Auto-generated constructor stub
	}

	public Long getNumSequencial() {
		return numSequencial;
	}

	public void setNumSequencial(Long numSequencial) {
		this.numSequencial = numSequencial;
	}
	
	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public Date getDtHrPagamento() {
		return dtHrPagamento;
	}

	public void setDtHrPagamento(Date dtHrPagamento) {
		this.dtHrPagamento = dtHrPagamento;
	}

	public Date getDtHrReferenciaPagamento() {
		return dtHrReferenciaPagamento;
	}

	public void setDtHrReferenciaPagamento(Date dtHrReferenciaPagamento) {
		this.dtHrReferenciaPagamento = dtHrReferenciaPagamento;
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
		PagamentoAluguel other = (PagamentoAluguel) obj;
		if (numSequencial == null) {
			if (other.numSequencial != null)
				return false;
		} else if (!numSequencial.equals(other.numSequencial))
			return false;
		return true;
	}

}
