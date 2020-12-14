package br.com.teste.prova.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-12-06T09:32:55.101-0300")
@StaticMetamodel(Member.class)
public class Member_ {
	public static volatile SingularAttribute<Member, Long> id;
	public static volatile SingularAttribute<Member, String> name;
	public static volatile SingularAttribute<Member, String> email;
	public static volatile SingularAttribute<Member, String> phoneNumber;
}
