package br.com.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-12-27T09:37:11.144-0300")
@StaticMetamodel(PagamentoAluguel.class)
public class PagamentoAluguel_ {
	public static volatile SingularAttribute<PagamentoAluguel, Long> numSequencial;
	public static volatile SingularAttribute<PagamentoAluguel, Aluguel> aluguel;
	public static volatile SingularAttribute<PagamentoAluguel, Date> dtHrPagamento;
	public static volatile SingularAttribute<PagamentoAluguel, Date> dtHrReferenciaPagamento;
	public static volatile SingularAttribute<PagamentoAluguel, String> recebedor;
}
