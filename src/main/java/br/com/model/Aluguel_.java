package br.com.model;

import br.com.model.Aluguel.SituacaoAluguel;
import br.com.model.Aluguel.StatusAluguel;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-12-23T13:57:06.414-0300")
@StaticMetamodel(Aluguel.class)
public class Aluguel_ {
	public static volatile SingularAttribute<Aluguel, Long> numSequencial;
	public static volatile SingularAttribute<Aluguel, StatusAluguel> status;
	public static volatile SingularAttribute<Aluguel, Casa> casa;
	public static volatile SingularAttribute<Aluguel, Inquilino> inquilino;
	public static volatile SingularAttribute<Aluguel, Date> dtHrAluguel;
	public static volatile ListAttribute<Aluguel, PagamentoAluguel> pagamentosAluguelList;
	public static volatile SingularAttribute<Aluguel, Integer> vencimento;
	public static volatile SingularAttribute<Aluguel, Double> valor;
	public static volatile SingularAttribute<Aluguel, String> observacao;
	public static volatile SingularAttribute<Aluguel, SituacaoAluguel> situacao;
}
