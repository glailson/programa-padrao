package br.com.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-12-22T12:31:13.794-0300")
@StaticMetamodel(PagamentoAluguel.class)
public class PagamentoAluguel_ {
	public static volatile SingularAttribute<PagamentoAluguel, Long> numSequencial;
	public static volatile SingularAttribute<PagamentoAluguel, Aluguel> aluguel;
	public static volatile SingularAttribute<PagamentoAluguel, Date> dtHrPagamento;
	public static volatile SingularAttribute<PagamentoAluguel, Date> dtHrReferenciaPagamento;
}
