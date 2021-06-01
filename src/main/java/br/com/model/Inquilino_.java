package br.com.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-04-08T10:09:36.663-0300")
@StaticMetamodel(Inquilino.class)
public class Inquilino_ {
	public static volatile SingularAttribute<Inquilino, Long> numSequencial;
	public static volatile SingularAttribute<Inquilino, String> nome;
	public static volatile SingularAttribute<Inquilino, String> nomeGuerra;
	public static volatile SingularAttribute<Inquilino, String> cpf;
	public static volatile SingularAttribute<Inquilino, String> observacao;
	public static volatile SingularAttribute<Inquilino, String> telefone;
	public static volatile SingularAttribute<Inquilino, String> profissao;
	public static volatile SingularAttribute<Inquilino, Date> dtHrCadastro;
	public static volatile SingularAttribute<Inquilino, String> nomeFiador;
	public static volatile SingularAttribute<Inquilino, String> telefoneFiador;
}
