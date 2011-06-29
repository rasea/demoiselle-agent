package br.gov.frameworkdemoiselle.security;

import java.io.Serializable;
import java.util.List;

import br.gov.frameworkdemoiselle.exception.RaseaException;

public interface RaseaContext extends Serializable {

	boolean authenticate() throws RaseaException;

	List<Permission> getPermissions(String username) throws RaseaException;

	User getUser(String name) throws RaseaException;
	
	Resource getResource(String name) throws RaseaException;

	void addResource(Resource resource) throws RaseaException;

	Application getApplication(String name) throws RaseaException;

	void addApplication(Application application) throws RaseaException;

	void assignOwner(String username, Application application) throws RaseaException;

	Operation getOperation(String name) throws RaseaException;

	void addOperation(Operation operation) throws RaseaException;

	List<Operation> getOperations(Resource resource, Application application) throws RaseaException;

	void addPermission(Permission permission) throws RaseaException;

	void grantPermission(Permission permission, Role role) throws RaseaException;

	List<Role> getRoles(String username, Application application) throws RaseaException;

	List<Role> getRoles(Application application) throws RaseaException;

	void addRole(Role role, Application application) throws RaseaException;

	void assignUser(String username, Role role, Application application) throws RaseaException;

}
