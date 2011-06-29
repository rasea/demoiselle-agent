package br.gov.frameworkdemoiselle.config;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

//@SessionScoped
@Configuration(prefix = "frameworkdemoiselle.application")
public class ApplicationConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Name("name")
	private String name = "default-app-name";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
