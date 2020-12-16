package br.com.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-12-16T11:01:34.843-0300")
@StaticMetamodel(Casa.class)
public class Casa_ {
	public static volatile SingularAttribute<Casa, Long> numSequencial;
	public static volatile SingularAttribute<Casa, Date> dtHrCadastro;
	public static volatile SingularAttribute<Casa, String> rua;
	public static volatile SingularAttribute<Casa, String> bairro;
	public static volatile SingularAttribute<Casa, String> cidade;
	public static volatile SingularAttribute<Casa, String> observacao;
	public static volatile SingularAttribute<Casa, Integer> numero;
}
