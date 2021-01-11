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
import javax.validation.constraints.Size;

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
	@Size(max=60)
    @Column(name="pausuariorecebedor")
    private String recebedor;
	
	public PagamentoAluguel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getNumSequencial() {
		return numSequencial;
	}

	@Override
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

	public String getRecebedor() {
		return recebedor;
	}

	public void setRecebedor(String recebedor) {
		this.recebedor = recebedor;
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
