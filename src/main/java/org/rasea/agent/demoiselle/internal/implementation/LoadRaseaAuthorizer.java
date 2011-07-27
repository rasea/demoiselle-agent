/*
 * Rasea Agent Demoiselle
 * 
 * Copyright (c) 2008, Rasea <http://rasea.org>. All rights reserved.
 *
 * Rasea Extensions is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, see <http://gnu.org/licenses>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.rasea.agent.demoiselle.internal.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.rasea.agent.demoiselle.config.ApplicationConfig;
import org.rasea.agent.demoiselle.exception.RaseaException;
import org.rasea.agent.demoiselle.security.Application;
import org.rasea.agent.demoiselle.security.Operation;
import org.rasea.agent.demoiselle.security.Permission;
import org.rasea.agent.demoiselle.security.RaseaContext;
import org.rasea.agent.demoiselle.security.Resource;
import org.rasea.agent.demoiselle.security.Role;

import br.gov.frameworkdemoiselle.security.Authorizer;

@Mode
@SessionScoped
public class LoadRaseaAuthorizer implements Authorizer {

	private static final long serialVersionUID = 1L;

	@Inject
	private RaseaCredentialImpl credential;

	@Inject
	private ApplicationConfig appConfig;

	@Inject
	private RaseaContext service;

	private final Map<String, Resource> resources = new HashMap<String, Resource>();

	private final Map<String, Operation> operations = new HashMap<String, Operation>();

	private final Map<String, Permission> permissions = new HashMap<String, Permission>();

	private Role role;

	private Application application;

	@Override
	public boolean hasRole(String role) {
		return true;
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		boolean result = false;

		try {
			this.getApplication();
			this.getResource(resource);
			this.getOperation(operation);
			this.getPermission(resource, operation);
			
		} catch (RaseaException e) {
			// TODO Colocar uma mensagem amigável para o programador saber o que ocorreu.
			e.printStackTrace();
		}

		result = true;
		return result;
	}

	private Resource getResource(final String name) throws RaseaException {
		Resource result = resources.get(name);

		if (result == null) {
			result = this.loadResource(name);
			resources.put(name, result);
		}

		return result;
	}

	private Resource loadResource(final String name) throws RaseaException {
		Resource result;
		
		try {
			result = service.getResource(name);
		} catch (Exception e) {
			result = new Resource(name, name);
			service.addResource(result);
		}

		return result;
	}

	private Operation getOperation(final String name) throws RaseaException {
		Operation result = operations.get(name);

		if (result == null) {
			result = this.loadOperation(name);
			operations.put(name, result);
		}

		return result;
	}

	private Operation loadOperation(final String name) throws RaseaException {
		Operation result;
		
		try {
			result = service.getOperation(name);
		} catch (Exception e) {
			result = new Operation(name);
			service.addOperation(result);
		}

		return result;
	}

	private Permission getPermission(final String resource, final String operation) throws RaseaException {
		final String key = resource + "\n" + operation;
		Permission result = this.permissions.get(key);

		if (result == null) {
			result = this.loadPermission(resource, operation);
			this.permissions.put(key, result);
		}

		return result;
	}

	private Permission loadPermission(final String resource, final String operation) throws RaseaException {
		Permission result = null;
		Resource res = new Resource(resource);

		for (final Operation op : service.getOperations(res, getApplication())) {
			if (op.getName().equals(operation)) {
				result = new Permission(res, op);
				break;
			}
		}

		if (result == null) {
			result = new Permission(res, new Operation(operation));
			service.addPermission(result);
		}

		service.grantPermission(result, new Role(getRole().getName()));

		return result;
	}

	private Role getRole() throws RaseaException {
		if (this.role == null) {
			this.role = this.loadRole();
		}

		return this.role;
	}

	private Role loadRole() throws RaseaException {
		Role result = null;
		final String username = credential.getUsername();
		final Application app = getApplication();

		List<Role> roles = service.getRoles(username, app);
		if (roles != null && !roles.isEmpty()) {
			result = roles.get(0);
		}

		if (result == null) {
			roles = service.getRoles(app);
			if (roles != null && !roles.isEmpty()) {
				result = roles.get(0);
			}
		}

		if (result == null) {
			result = new Role("admin", "admin");
			service.addRole(result, app);
			service.assignUser(username, result, app);
		}

		return result;
	}

	private Application getApplication() throws RaseaException {
		if (this.application == null) {
			this.application = this.loadApplication();
		}

		return this.application;
	}

	private Application loadApplication() throws RaseaException {
		String name = appConfig.getName();
		Application result = null;

		try {
			result = service.getApplication(name);

		} catch (Exception cause) {
			result = new Application(name, name);
			service.addApplication(result);
		}

		service.assignOwner(credential.getUsername(), new Application(name));
		return result;
	}
}
