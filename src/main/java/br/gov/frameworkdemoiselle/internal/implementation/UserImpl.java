package br.gov.frameworkdemoiselle.internal.implementation;

import br.gov.frameworkdemoiselle.internal.proxy.UserType;
import br.gov.frameworkdemoiselle.security.User;

public class UserImpl implements User {

	private static final long serialVersionUID = 1L;

	private UserType delegate;

	public UserImpl(UserType delegate) {
		this.delegate = delegate;
	}

	@Override
	public String getId() {
		return delegate.getName();
	}

	@Override
	public Object getAttribute(Object key) {
		return null;
	}

	@Override
	public void setAttribute(Object key, Object value) {
	}
}
