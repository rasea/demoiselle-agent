package br.gov.frameworkdemoiselle.security;

public class Resource {

	private String name;

	private String description;

	public Resource(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Resource(String name) {
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
