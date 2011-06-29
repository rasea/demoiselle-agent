package br.gov.frameworkdemoiselle.internal.producer;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Strategy;
import br.gov.frameworkdemoiselle.config.RaseaAgentConfig;
import br.gov.frameworkdemoiselle.internal.implementation.DefaultRaseaAuthorizator;
import br.gov.frameworkdemoiselle.internal.implementation.LoadRaseaAuthorizator;
import br.gov.frameworkdemoiselle.internal.implementation.MockRaseaAuthorizator;
import br.gov.frameworkdemoiselle.internal.implementation.Mode;
import br.gov.frameworkdemoiselle.security.Authorizator;
import br.gov.frameworkdemoiselle.util.Beans;

public class AuthorizatorStrategyProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RaseaAgentConfig agentConfig;

	@Strategy
	@Produces
	@SuppressWarnings("serial")
	public Authorizator create() {
		Class<? extends Authorizator> authorizatorClass;

		switch (agentConfig.getMode()) {
		case LOAD:
			authorizatorClass = LoadRaseaAuthorizator.class;
			break;

		case MOCK:
			authorizatorClass = MockRaseaAuthorizator.class;
			break;

		default:
			authorizatorClass = DefaultRaseaAuthorizator.class;
		}

		return Beans.getReference(authorizatorClass, new AnnotationLiteral<Mode>() {
		});
	}
}
