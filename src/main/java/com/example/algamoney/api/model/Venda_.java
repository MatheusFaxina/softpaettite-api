package com.example.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Venda_ {

	public static volatile SingularAttribute<Venda, LocalDate> cadastro;
	public static volatile ListAttribute<Venda, ItemVenda> itens;
	public static volatile SingularAttribute<Venda, Long> codigo;
	public static volatile SingularAttribute<Venda, Mesa> mesa;
	public static volatile SingularAttribute<Venda, BigDecimal> valorTotal;

}

