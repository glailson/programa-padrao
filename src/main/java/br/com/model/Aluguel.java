package br.com.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tbaluguel")
public class Aluguel implements GenericEntity<Long> {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "alnumsequencial")
	private Long numSequencial;
	@Enumerated(EnumType.STRING)
	@Column(name = "restatusparaclientes")
	private StatusAluguel status;
	@ManyToOne
	@JoinColumn(name = "alcasa", referencedColumnName = "canumsequencial")
	private Casa casa;
	@JoinColumn(name = "alinquilino", referencedColumnName = "innumsequencial")
    @ManyToOne
    private Inquilino inquilino;
	@Column(name = "aldthraluguel")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dtHrAluguel;
	@OneToMany(mappedBy = "aluguel",cascade = CascadeType.ALL, orphanRemoval=true,fetch= FetchType.LAZY)
	private List<PagamentoAluguel> pagamentosAluguelList;
	@Column(name = "alvencimento")
	private Integer vencimento;
	@Column(name = "alvalor")
	private Double valor;
	
	public Aluguel() {
		// TODO Auto-generated constructor stub
	}

	public Long getNumSequencial() {
		return numSequencial;
	}

	public void setNumSequencial(Long numSequencial) {
		this.numSequencial = numSequencial;
	}
	
	public StatusAluguel getStatus() {
		return status;
	}

	public void setStatus(StatusAluguel status) {
		this.status = status;
	}

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}

	public Date getDtHrAluguel() {
		return dtHrAluguel;
	}

	public void setDtHrAluguel(Date dtHrAluguel) {
		this.dtHrAluguel = dtHrAluguel;
	}

	public List<PagamentoAluguel> getPagamentosAluguelList() {
		return pagamentosAluguelList;
	}

	public void setPagamentosAluguelList(List<PagamentoAluguel> pagamentosAluguelList) {
		this.pagamentosAluguelList = pagamentosAluguelList;
	}

	public Integer getVencimento() {
		return vencimento;
	}

	public void setVencimento(Integer vencimento) {
		this.vencimento = vencimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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
		Aluguel other = (Aluguel) obj;
		if (numSequencial == null) {
			if (other.numSequencial != null)
				return false;
		} else if (!numSequencial.equals(other.numSequencial))
			return false;
		return true;
	}
	
	public static enum StatusAluguel {
		ATIVO(0L,"Ativo"),
		INATIVO(1L,"Inativo");
		
		private Long numSequencial;
		private String descricao;
		
		private StatusAluguel() {
		}
		
		private StatusAluguel(Long numSequencial, String descricao) {
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
