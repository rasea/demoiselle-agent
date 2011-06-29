package br.gov.frameworkdemoiselle.internal.implementation;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.gov.frameworkdemoiselle.internal.proxy.Credentials;
import br.gov.frameworkdemoiselle.security.RaseaCredential;

@SessionScoped
@Named("credential")
public class RaseaCredentialImpl implements RaseaCredential {

	private static final long serialVersionUID = 1L;

	private final Credentials delegate;

	public RaseaCredentialImpl() {
		delegate = new Credentials();
	}

	public String getUsername() {
		return delegate.getUsername();
	}

	public void setUsername(String username) {
		delegate.setUsername(username);
	}

	public Credentials getDelegate() {
		return delegate;
	}

	public String getPassword() {
		return delegate.getPassword();
	}

	public void setPassword(String password) {
		delegate.setPassword(password);
	}
}
