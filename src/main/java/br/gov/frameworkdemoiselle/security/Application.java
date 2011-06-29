package br.gov.frameworkdemoiselle.security;

import java.io.Serializable;

public class Application implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String description;

	public Application(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Application(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
