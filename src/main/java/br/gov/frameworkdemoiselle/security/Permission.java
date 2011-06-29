package br.gov.frameworkdemoiselle.security;

public class Permission {

	private final Resource resource;

	private final Operation operation;

	public Permission(Resource resource, Operation operation) {
		this.resource = resource;
		this.operation = operation;
	}

	public Resource getResource() {
		return resource;
	}

	public Operation getOperation() {
		return operation;
	}
}
