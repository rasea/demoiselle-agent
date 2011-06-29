package br.gov.frameworkdemoiselle.internal.implementation;

import javax.enterprise.context.SessionScoped;

import br.gov.frameworkdemoiselle.security.Authorizator;

@Mode
@SessionScoped
public class MockRaseaAuthorizator implements Authorizator {

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
