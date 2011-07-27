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

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.rasea.agent.demoiselle.config.ApplicationConfig;
import org.rasea.agent.demoiselle.exception.RaseaException;
import org.rasea.agent.demoiselle.internal.proxy.AccessControlV1;
import org.rasea.agent.demoiselle.internal.proxy.ApplicationNameRequest;
import org.rasea.agent.demoiselle.internal.proxy.ApplicationRequest;
import org.rasea.agent.demoiselle.internal.proxy.ApplicationType;
import org.rasea.agent.demoiselle.internal.proxy.GrantPermissionRequest;
import org.rasea.agent.demoiselle.internal.proxy.MaintenanceV1;
import org.rasea.agent.demoiselle.internal.proxy.ManagementV1;
import org.rasea.agent.demoiselle.internal.proxy.OperationNameRequest;
import org.rasea.agent.demoiselle.internal.proxy.OperationRequest;
import org.rasea.agent.demoiselle.internal.proxy.OperationType;
import org.rasea.agent.demoiselle.internal.proxy.OperationsResponse;
import org.rasea.agent.demoiselle.internal.proxy.PermissionRequest;
import org.rasea.agent.demoiselle.internal.proxy.PermissionType;
import org.rasea.agent.demoiselle.internal.proxy.PermissionsResponse;
import org.rasea.agent.demoiselle.internal.proxy.ResourceNameRequest;
import org.rasea.agent.demoiselle.internal.proxy.ResourceRequest;
import org.rasea.agent.demoiselle.internal.proxy.ResourceType;
import org.rasea.agent.demoiselle.internal.proxy.RoleRequest;
import org.rasea.agent.demoiselle.internal.proxy.RoleType;
import org.rasea.agent.demoiselle.internal.proxy.RolesResponse;
import org.rasea.agent.demoiselle.internal.proxy.SimpleUserNameRequest;
import org.rasea.agent.demoiselle.internal.proxy.UserNameRequest;
import org.rasea.agent.demoiselle.internal.proxy.UserRoleRequest;
import org.rasea.agent.demoiselle.internal.proxy.UserType;
import org.rasea.agent.demoiselle.security.Application;
import org.rasea.agent.demoiselle.security.Operation;
import org.rasea.agent.demoiselle.security.Permission;
import org.rasea.agent.demoiselle.security.RaseaContext;
import org.rasea.agent.demoiselle.security.Resource;
import org.rasea.agent.demoiselle.security.Role;

import br.gov.frameworkdemoiselle.exception.ConfigurationException;
import br.gov.frameworkdemoiselle.security.User;

@SessionScoped
public class RaseaContextImpl implements RaseaContext {

	private static final long serialVersionUID = 1L;

	@Inject
	private ApplicationConfig appConfig;

	@Inject
	private AccessControlV1 accessControlPort;

	@Inject
	private ManagementV1 managementPort;

	@Inject
	private MaintenanceV1 maintenancePort;

	@Inject
	private RaseaCredentialImpl credential;

	private void handleException(Exception cause) throws RaseaException {
		throw new ConfigurationException(cause.getMessage(), cause);
	}

	@Override
	public boolean authenticate() throws RaseaException {
		boolean result = false;

		try {
			result = accessControlPort.authenticate(credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public List<Permission> getPermissions(String username) throws RaseaException {
		
		List<Permission> result = new ArrayList<Permission>();

		try {
			UserNameRequest request = new UserNameRequest();
			request.setUsername(username);
			request.setApplicationName(appConfig.getName());

			PermissionsResponse response = accessControlPort.userPermissions(request, credential.getDelegate());

			Resource resource;
			Operation operation;
			for (PermissionType type : response.getPermission()) {
				resource = new Resource(type.getResourceName());
				operation = new Operation(type.getOperationName());

				result.add(new Permission(resource, operation));
			}
		} catch (Exception ex) {
			handleException(ex);			
		}

		return result;
	}

	public Resource getResource(String name) throws RaseaException {
		Resource result = null;

		ResourceNameRequest request = new ResourceNameRequest();
		request.setApplicationName(appConfig.getName());
		request.setResourceName(name);

		try {
			ResourceType response = maintenancePort.resourceDetail(request, credential.getDelegate());

			if (response != null) {
				result = new Resource(response.getName(), response.getDisplayName());
			}

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public void addResource(Resource resource) throws RaseaException {
		try {
			ResourceType type = new ResourceType();
			type.setName(resource.getName());
			type.setDisplayName(resource.getDescription());

			ResourceRequest request = new ResourceRequest();
			request.setResource(type);
			request.setApplicationName(this.appConfig.getName());

			maintenancePort.addResource(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public Application getApplication(String name) throws RaseaException {
		Application result = null;

		try {
			ApplicationNameRequest request = new ApplicationNameRequest();
			request.setApplicationName(name);

			ApplicationType response = maintenancePort.applicationDetail(request, credential.getDelegate());
			result = new Application(response.getName(), response.getDisplayName());

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public void addApplication(Application application) throws RaseaException {
		ApplicationType type = new ApplicationType();
		type.setName(application.getName());
		type.setDisplayName(application.getDescription());

		try {
			ApplicationRequest request = new ApplicationRequest();
			request.setApplication(type);

			maintenancePort.addApplication(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public void assignOwner(String username, Application application) throws RaseaException {
		try {
			UserNameRequest request = new UserNameRequest();
			request.setApplicationName(application.getName());
			request.setUsername(username);
			maintenancePort.assignOwner(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public Operation getOperation(String name) throws RaseaException {
		Operation result = null;

		OperationNameRequest request = new OperationNameRequest();
		request.setApplicationName(appConfig.getName());
		request.setOperationName(name);

		try {
			OperationType response = maintenancePort.operationDetail(request, credential.getDelegate());

			if (response != null) {
				result = new Operation(response.getName());
			}

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public void addOperation(Operation operation) throws RaseaException {
		OperationType type = new OperationType();
		type.setName(operation.getName());
		type.setDisplayName(operation.getName());

		OperationRequest request = new OperationRequest();
		request.setApplicationName(appConfig.getName());
		request.setOperation(type);

		try {
			maintenancePort.addOperation(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public List<Operation> getOperations(Resource resource, Application application) throws RaseaException {
		List<Operation> result = new ArrayList<Operation>();

		ResourceNameRequest request = new ResourceNameRequest();
		request.setApplicationName(application.getName());
		request.setResourceName(resource.getName());

		try {
			OperationsResponse response = maintenancePort.resourceOperations(request, credential.getDelegate());

			if (response.getOperation() != null) {
				for (OperationType type : response.getOperation()) {
					result.add(new Operation(type.getName()));
				}
			}

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public void addPermission(Permission permission) throws RaseaException {
		PermissionType type = new PermissionType();

		type.setOperationName(permission.getOperation().getName());
		type.setResourceName(permission.getResource().getName());

		PermissionRequest request = new PermissionRequest();
		request.setApplicationName(appConfig.getName());
		request.setPermission(type);

		try {
			maintenancePort.addPermission(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public void grantPermission(Permission permission, Role role) throws RaseaException {
		PermissionType type = new PermissionType();
		type.setResourceName(permission.getResource().getName());
		type.setOperationName(permission.getOperation().getName());

		GrantPermissionRequest request = new GrantPermissionRequest();
		request.setApplicationName(appConfig.getName());
		request.setRoleName(role.getName());
		request.setPermission(type);
		request.setAllowed(true);

		try {
			managementPort.grantPermission(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	@Override
	public User getUser(String name) throws RaseaException {
		User result = null;

		try {
			SimpleUserNameRequest request = new SimpleUserNameRequest();
			request.setUsername(name);

			UserType response = accessControlPort.userDetail(request, credential.getDelegate());
			result = new UserImpl(response);

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public List<Role> getRoles(String username, Application application) throws RaseaException {
		List<Role> result = new ArrayList<Role>();

		UserNameRequest request = new UserNameRequest();
		request.setUsername(username);
		request.setApplicationName(application.getName());

		try {
			RolesResponse response = accessControlPort.assignedRoles(request, credential.getDelegate());

			if (response.getRole() != null) {
				for (RoleType type : response.getRole()) {
					result.add(new Role(type.getName(), type.getDisplayName()));
				}
			}

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;

	}

	public List<Role> getRoles(Application application) throws RaseaException {
		List<Role> result = new ArrayList<Role>();

		ApplicationNameRequest request = new ApplicationNameRequest();
		request.setApplicationName(application.getName());

		try {
			RolesResponse response = accessControlPort.listRoles(request, credential.getDelegate());

			if (response.getRole() != null) {
				for (RoleType type : response.getRole()) {
					result.add(new Role(type.getName(), type.getDisplayName()));
				}
			}

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;

	}

	public void addRole(Role role, Application application) throws RaseaException {
		RoleType type = new RoleType();
		type.setName(role.getName());
		type.setDisplayName(role.getDescription());

		RoleRequest request = new RoleRequest();
		request.setApplicationName(application.getName());
		request.setRole(type);

		try {
			managementPort.addRole(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public void assignUser(String username, Role role, Application application) throws RaseaException {
		UserRoleRequest request = new UserRoleRequest();
		request.setApplicationName(application.getName());
		request.setUsername(username);
		request.setRoleName(role.getName());

		try {
			managementPort.assignUser(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}
}
