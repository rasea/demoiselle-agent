package br.gov.frameworkdemoiselle.internal.producer;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Strategy;
import br.gov.frameworkdemoiselle.config.RaseaAgentConfig;
import br.gov.frameworkdemoiselle.internal.implementation.DefaultRaseaAuthorizer;
import br.gov.frameworkdemoiselle.internal.implementation.LoadRaseaAuthorizer;
import br.gov.frameworkdemoiselle.internal.implementation.MockRaseaAuthorizer;
import br.gov.frameworkdemoiselle.internal.implementation.Mode;
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.util.Beans;

public class AuthorizerStrategyProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RaseaAgentConfig agentConfig;

	@Strategy
	@Produces
	@SuppressWarnings("serial")
	public Authorizer create() {
		Class<? extends Authorizer> authorizerClass;

		switch (agentConfig.getMode()) {
		case LOAD:
			authorizerClass = LoadRaseaAuthorizer.class;
			break;

		case MOCK:
			authorizerClass = MockRaseaAuthorizer.class;
			break;

		default:
			authorizerClass = DefaultRaseaAuthorizer.class;
		}

		return Beans.getReference(authorizerClass, new AnnotationLiteral<Mode>() {
		});
	}
}
