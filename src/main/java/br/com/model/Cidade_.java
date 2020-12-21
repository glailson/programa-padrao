package br.com.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-12-21T09:09:00.735-0300")
@StaticMetamodel(Cidade.class)
public class Cidade_ {
	public static volatile SingularAttribute<Cidade, Long> numSequencial;
	public static volatile SingularAttribute<Cidade, String> nome;
	public static volatile SingularAttribute<Cidade, Estado> estado;
	public static volatile SingularAttribute<Cidade, Date> dtHrCadastro;
}
