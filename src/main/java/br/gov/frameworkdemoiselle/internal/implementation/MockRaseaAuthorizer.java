package br.gov.frameworkdemoiselle.internal.implementation;

import javax.enterprise.context.SessionScoped;

import br.gov.frameworkdemoiselle.security.Authorizer;

@Mode
@SessionScoped
public class MockRaseaAuthorizer implements Authorizer {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean hasRole(String role) {
		return true;
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		return true;
	}
}
