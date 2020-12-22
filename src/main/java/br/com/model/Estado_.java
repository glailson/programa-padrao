package br.com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-12-21T17:43:46.771-0300")
@StaticMetamodel(Estado.class)
public class Estado_ {
	public static volatile SingularAttribute<Estado, Long> numSequencial;
	public static volatile SingularAttribute<Estado, String> nome;
	public static volatile ListAttribute<Estado, Cidade> cidadeList;
}
