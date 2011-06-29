package br.gov.frameworkdemoiselle.security;

import java.io.Serializable;

public interface RaseaCredential extends Serializable {

	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);
}
